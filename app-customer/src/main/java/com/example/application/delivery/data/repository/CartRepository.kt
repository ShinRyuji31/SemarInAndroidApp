package com.example.application.delivery.data.repository

import android.util.Log
import com.example.application.delivery.data.dto.ItemOrderInsertDto
import com.example.application.delivery.data.dto.LocationInsertDto
import com.example.application.delivery.data.dto.OrderInsertDto
import com.example.application.delivery.data.dto.OrderItemInsertDto
import com.example.application.delivery.data.dto.StoreLocationDto
import com.example.application.delivery.data.local.CartDataStore
import com.example.application.delivery.data.model.CartItem
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.UUID

class CartRepository(
    private val dataStore: CartDataStore,
    private val supabase: SupabaseClient
) {
    fun getCartItems(): Flow<List<CartItem>> = dataStore.getCartItems()

    suspend fun saveCartItems(items: List<CartItem>) {
        dataStore.saveCartItems(items)
    }

    suspend fun checkoutOrder(
        userId: String,
        items: List<CartItem>,
        total: Int,
        userLat: Double,
        userLon: Double,
        userAddress: String
    ): Result<Unit> {
        return try {
            val storeId = items.firstOrNull()?.storeId ?: throw Exception("Cart is empty")
            val storeType = items.firstOrNull()?.storeType ?: "FOOD"

            // 1. CARI LOKASI TOKO (Untuk Pickup Location)
            val storeInfo = supabase.postgrest["STORE"]
                .select {
                    filter { eq("store_id", storeId) }
                }.decodeSingle<StoreLocationDto>()
            val pickupLocationId = storeInfo.locationId

            // 2. INSERT LOKASI USER (Untuk Destination Location)
            val destinationLocationId = UUID.randomUUID().toString()
            val locationDto = LocationInsertDto(
                locationId = destinationLocationId,
                latitude = userLat,
                longitude = userLon,
                address = userAddress
            )
            supabase.postgrest["LOCATION"].insert(locationDto)

            val orderId = UUID.randomUUID().toString()
            val now = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }.format(Date())

            // 3. INSERT ORDER (Sambungin Pickup & Destination)
            val orderDto = OrderInsertDto(
                orderId = orderId,
                orderDate = now,
                totalPrice = total.toDouble(),
                orderStatus = "PENDING",
                customerId = userId,
                pickupLocationId = pickupLocationId,       // Dari Toko
                destinationLocationId = destinationLocationId // Dari User
            )
            supabase.postgrest["ORDER"].insert(orderDto)

            // 4. INSERT ITEM_ORDER
            val itemOrderDto = ItemOrderInsertDto(
                orderId = orderId,
                orderType = storeType,
                storeId = storeId
            )
            supabase.postgrest["ITEM_ORDER"].insert(itemOrderDto)

            // 5. INSERT ORDER_ITEM
            val orderItems = items.flatMap { cartItem ->
                List(cartItem.quantity) {
                    OrderItemInsertDto(
                        orderId = orderId,
                        productId = cartItem.storeInventoryId
                    )
                }
            }
            if (orderItems.isNotEmpty()) {
                supabase.postgrest["ORDER_ITEM"].insert(orderItems)
            }

            saveCartItems(emptyList())
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("CartRepository", "Checkout failed: ${e.message}")
            Result.failure(e)
        }
    }
}