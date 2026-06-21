package com.example.application.orderhistory.data.repository

import android.util.Log
import androidx.compose.ui.graphics.Color
import com.example.application.core.R as coreR
import com.example.application.R
import com.example.application.orderhistory.data.model.OrderHistory
import com.example.application.orderhistory.data.model.OrderType
import com.example.application.orderhistory.data.dto.OrderDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrderHistoryRepository(
    private val supabase: SupabaseClient
) {
    suspend fun fetchOrderHistory(): Result<List<OrderHistory>> = withContext(Dispatchers.IO) {
        try {
            val dtos = supabase.postgrest["ORDER"]
                .select(columns = Columns.raw("*, ITEM_ORDER(*, STORE(*, LOCATION(*)))")) {
                    order(column = "order_date", order = Order.DESCENDING)
                }
                .decodeList<OrderDto>()

            val histories = dtos.map { dto ->
                val itemOrder = dto.itemOrders?.firstOrNull()
                val store = itemOrder?.store

                val isFood = itemOrder?.orderType?.contains("FOOD", ignoreCase = true) == true

                val name = store?.storeName ?: "Anter-In Ride"

                val details = store?.location?.address ?: "Jarak: ${dto.distance ?: 0.0} km"

                val type = if (store != null) OrderType.FOOD else OrderType.ANTERIN

                val themeColor = if (store != null) {
                    if (isFood) Color(0xFFFFD600) else Color(0xFF008938)
                } else {
                    Color.White
                }

                val imageRes = if (store != null) coreR.drawable.dummy else R.drawable.ic_bike

                val formattedDate = dto.orderDate.replace("T", ", ").substringBeforeLast(":") + " WIB"

                OrderHistory(
                    id = dto.orderId,
                    name = name,
                    details = details,
                    dateTime = formattedDate,
                    price = dto.totalPrice.toInt(),
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