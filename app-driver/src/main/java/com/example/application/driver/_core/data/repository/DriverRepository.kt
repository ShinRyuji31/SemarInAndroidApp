package com.example.application.driver._core.data.repository

import android.util.Log
import com.example.application.driver._core.data.model.DriverDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

class DriverRepository(private val supabase: SupabaseClient) {
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
}