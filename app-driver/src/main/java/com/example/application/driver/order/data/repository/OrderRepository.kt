package com.example.application.driver.order.data.repository

import android.util.Log
import com.example.application.driver._core.data.model.DriverDto
import com.example.application.driver._core.data.model.DriverLocationDto
import com.example.application.driver.order.data.dto.ActiveOrderDto
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
            Log.e("DriverRepository", "Error checking driver status", e)
            false
        }
    }

    suspend fun updateDriverLocation(driverId: String, lat: Double, lon: Double) {
        try {
            val locationData = DriverLocationDto(driverId, lat, lon, getCurrentIsoTimestamp())
            supabase.postgrest["DRIVER_LOCATION"].upsert(locationData)
        } catch (e: Exception) {
            Log.e("DriverRepository", "Gagal update lokasi driver", e)
        }
    }

    private val orderSelectColumns = Columns.raw(
        "order_id, order_status, distance, total_price, pickup:LOCATION!pickup_location_id(address), destination:LOCATION!destination_location_id(address), ITEM_ORDER(STORE(store_name))"
    )

    suspend fun getIncomingOrder(driverId: String): ActiveOrderDto? {
        return try {
            val orders = supabase.postgrest["ORDER"].select(columns = orderSelectColumns) {
                filter {
                    eq("driver_id", driverId)
                    eq("order_status", "PENDING")
                }
            }.decodeList<ActiveOrderDto>()
            orders.firstOrNull()
        } catch (e: Exception) {
            Log.e("DriverRepository", "Gagal narik incoming order", e)
            null
        }
    }

    suspend fun getActiveOrder(driverId: String): ActiveOrderDto? {
        return try {
            val orders = supabase.postgrest["ORDER"].select(columns = orderSelectColumns) {
                filter {
                    eq("driver_id", driverId)
                    isIn("order_status", listOf("ACCEPTED", "PICKING_UP", "DELIVERING", "WAITING_PAYMENT"))
                }
            }.decodeList<ActiveOrderDto>()
            orders.firstOrNull()
        } catch (e: Exception) {
            Log.e("DriverRepository", "Gagal narik active order", e)
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
            Log.e("DriverRepository", "Gagal update status order", e)
            false
        }
    }
}