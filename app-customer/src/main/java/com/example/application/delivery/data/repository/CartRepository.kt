package com.example.application.delivery.data.repository

import com.example.application._core.data.maps.repository.MapsRepository
import com.example.application.delivery.data.dto.LocationInsertDto
import com.example.application.delivery.data.dto.OrderInsertDto
import com.example.application.delivery.data.dto.OrderItemInsertDto
import com.example.application.delivery.data.dto.StoreLocationDto
import com.example.application.delivery.data.local.CartDataStore
import com.example.application.delivery.data.model.CartItem
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class CartRepository(
    private val dataStore: CartDataStore,
    private val supabase: SupabaseClient,
    private val mapsRepository: MapsRepository
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
                .select { filter { eq("store_id", storeId) } }
                .decodeSingle<StoreLocationDto>()
            val pickupLocationId = storeInfo.locationId

            val storeLocation = supabase.postgrest["LOCATION"]
                .select { filter { eq("location_id", pickupLocationId) } }
                .decodeSingle<LocationInsertDto>()

            // 2. HITUNG JARAK PAKAI MAPS REPOSITORY
            val routeResult = mapsRepository.getRoute(
                startLat = storeLocation.latitude,
                startLon = storeLocation.longitude,
                endLat = userLat,
                endLon = userLon
            )
            val distanceInMeters = routeResult.getOrNull()?.distance ?: 0.0
            val distanceInKm = distanceInMeters / 1000.0

            // 3. INSERT LOKASI USER (Alamat Pengiriman)
            val destinationLocationId = UUID.randomUUID().toString()
            val locationDto = LocationInsertDto(
                locationId = destinationLocationId,
                latitude = userLat,
                longitude = userLon,
                address = userAddress
            )
            supabase.postgrest["LOCATION"].insert(locationDto)

            val orderId = UUID.randomUUID().toString()

            val now = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", java.util.Locale.US).apply {
                timeZone = java.util.TimeZone.getTimeZone("Asia/Jakarta")
            }.format(java.util.Date())

            // 4. INSERT ORDER (Sekarang masukin distance)
            val orderDto = OrderInsertDto(
                orderId = orderId,
                orderDate = now,
                totalPrice = total.toDouble(),
                orderStatus = "PENDING",
                customerId = userId,
                pickupLocationId = pickupLocationId,
                destinationLocationId = destinationLocationId,
                distance = distanceInKm,
                isAnterin = false
            )
            supabase.postgrest["ORDER"].insert(orderDto)


            // 6. INSERT ORDER_ITEM
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

            // 7. BERSIHKAN KERANJANG
            saveCartItems(emptyList())

            Result.success(Unit)
        } catch (e: Exception) {
            android.util.Log.e("CartRepository", "Checkout failed: ${e.message}")
            Result.failure(e)
        }
    }
}