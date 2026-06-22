package com.example.application.order.data.repository

import android.util.Log
import com.example.application.order.data.dto.ActiveOrderDto
import com.example.application.order.data.dto.DriverDto
import com.example.application.order.data.dto.DriverLocationDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class OrderRepository(private val supabase: SupabaseClient) {

    private fun getCurrentIsoTimestamp(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.US)
        sdf.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
        return sdf.format(Date())
    }

    suspend fun checkIsDriverActive(userId: String): Boolean {
        return try {
            val driver = supabase.postgrest["DRIVER"]
                .select { filter { eq("driver_id", userId) } }
                .decodeSingleOrNull<DriverDto>()
            driver != null
        } catch (e: Exception) {
            Log.e("OrderRepository", "Error checking driver status", e)
            false
        }
    }

    suspend fun updateDriverLocation(driverId: String, lat: Double, lon: Double) {
        try {
            val locationData = DriverLocationDto(driverId, lat, lon, getCurrentIsoTimestamp())
            supabase.postgrest["DRIVER_LOCATION"].upsert(locationData)
        } catch (e: Exception) {
            Log.e("OrderRepository", "Gagal update lokasi driver", e)
        }
    }

    private val orderSelectColumns = Columns.Companion.raw(
        "order_id, order_status, is_anterin, distance, total_price, pickup:LOCATION!pickup_location_id(address), destination:LOCATION!destination_location_id(address), ORDER_ITEM(PRODUCT(STORE(store_name)))"
    )

    suspend fun getIncomingOrder(userId: String, isDriver: Boolean = true): ActiveOrderDto? {
        val column = if (isDriver) "driver_id" else "customer_id"
        return try {
            val orders = supabase.postgrest["ORDER"].select(columns = orderSelectColumns) {
                filter {
                    eq(column, userId)
                    eq("order_status", "PENDING")
                }
            }.decodeList<ActiveOrderDto>()
            orders.firstOrNull()
        } catch (e: Exception) {
            Log.e("OrderRepository", "Gagal narik incoming order", e)
            null
        }
    }

    suspend fun getActiveOrder(userId: String, isDriver: Boolean = true): ActiveOrderDto? {
        val column = if (isDriver) "driver_id" else "customer_id"
        return try {
            val orders = supabase.postgrest["ORDER"].select(columns = orderSelectColumns) {
                filter {
                    eq(column, userId)
                    val statuses = if (isDriver) {
                        listOf("ACCEPTED", "PICKING_UP", "DELIVERING", "WAITING_PAYMENT")
                    } else {
                        listOf("PENDING", "ACCEPTED", "PICKING_UP", "DELIVERING", "WAITING_PAYMENT")
                    }
                    isIn("order_status", statuses)
                }
            }.decodeList<ActiveOrderDto>()
            orders.firstOrNull()
        } catch (e: Exception) {
            Log.e("OrderRepository", "Gagal narik active order", e)
            null
        }
    }

    suspend fun updateOrderStatus(orderId: String, newStatus: String): Boolean {
        return try {
            supabase.postgrest["ORDER"].update(
                mapOf("order_status" to newStatus)
            ) {
                filter { eq("order_id", orderId) }
            }
            true
        } catch (e: Exception) {
            Log.e("OrderRepository", "Gagal update status order", e)
            false
        }
    }
}