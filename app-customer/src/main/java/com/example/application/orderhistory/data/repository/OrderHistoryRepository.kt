package com.example.application.orderhistory.data.repository

import android.util.Log
import androidx.compose.ui.graphics.Color
import com.example.application.R
import com.example.application.core.R as coreR
import com.example.application.order.data.dto.ActiveOrderDto
import com.example.application.orderhistory.data.model.OrderHistory
import com.example.application.orderhistory.data.model.OrderType
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrderHistoryRepository(
    private val supabase: SupabaseClient
) {
    suspend fun fetchOrderHistory(userId: String): Result<List<OrderHistory>> = withContext(Dispatchers.IO) {
        try {
            val dtos = supabase.postgrest["ORDER"]
                .select(columns = Columns.raw("*, pickup:LOCATION!pickup_location_id(address), destination:LOCATION!destination_location_id(address), ORDER_ITEM(PRODUCT(STORE(*, LOCATION(*))))")) {
                    filter {
                        eq("customer_id", userId)
                        eq("order_status", "COMPLETED")
                    }
                    order(column = "order_date", order = Order.DESCENDING)
                }
                .decodeList<ActiveOrderDto>()

            val histories = dtos.map { dto ->
                val store = dto.orderItems?.firstOrNull()?.product?.store

                val type = if (dto.isAnterin == true) OrderType.ANTERIN else OrderType.FOOD

                val isFood = store?.storeType?.equals("FOOD", ignoreCase = true) == true

                val name = store?.storeName ?: "Anter-In Ride"

                val details = store?.location?.address ?: "Jarak: ${dto.distance ?: 0.0} km"

                val themeColor = if (dto.isAnterin == false && store != null) {
                    if (isFood) Color(0xFFFFD600) else Color(0xFF008938)
                } else {
                    Color.White
                }

                val imageRes = if (dto.isAnterin == false && store != null) coreR.drawable.dummy else R.drawable.ic_bike

                val formattedDate = "Waktu Pemesanan Selesai"

                OrderHistory(
                    id = dto.orderId,
                    name = name,
                    details = details,
                    dateTime = formattedDate,
                    price = dto.totalPrice?.toInt() ?: 0,
                    imageRes = imageRes,
                    type = type,
                    themeColor = themeColor
                )
            }
            Result.success(histories)
        } catch (e: Exception) {
            Log.e("OrderHistoryRepo", "Error fetching order history", e)
            Result.failure(e)
        }
    }
}