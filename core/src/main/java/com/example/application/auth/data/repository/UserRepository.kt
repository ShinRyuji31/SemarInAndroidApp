package com.example.application.auth.data.repository

import android.util.Log
import com.example.application.auth.data.model.Customer
import com.example.application.auth.data.model.User
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionStatus
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

// 👇 INI YANG KETINGGALAN BRO, TAMBAHIN driverTypeId DI SINI
@Serializable
data class DriverInsertDto(
    @SerialName("driver_id") val driverId: String,
    @SerialName("student_card") val studentCard: String,
    @SerialName("driver_status") val driverStatus: String,
    @SerialName("driver_type_id") val driverTypeId: String
)

class UserRepository(supabaseClient: SupabaseClient) {

    private val auth = supabaseClient.auth
    private val postgrest = supabaseClient.postgrest

    val sessionStatus: Flow<SessionStatus> = auth.sessionStatus

    private fun getCurrentIsoTimestamp(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return sdf.format(Date())
    }

    suspend fun signUp(
        email: String, password: String, username: String,
        firstName: String, lastName: String, phoneNumber: String
    ): Result<Unit> {
        return try {
            auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            val userId = auth.currentUserOrNull()?.id ?: throw Exception("Sign up successful but could not retrieve User ID.")

            val now = getCurrentIsoTimestamp()
            val userProfile = User(
                id = userId, username = username, firstName = firstName,
                lastName = lastName.ifBlank { null },
                email = email, phoneNumber = phoneNumber,
                timeCreated = now, timeUpdated = now
            )
            val customerProfile = Customer(customerId = userId)

            postgrest.from("PROFILE").insert(userProfile)
            postgrest.from("CUSTOMER").insert(customerProfile)

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signUpDriver(
        email: String,
        password: String,
        username: String,
        firstName: String,
        lastName: String,
        phoneNumber: String,
        driverTypeId: String
    ): Result<Unit> {
        return try {
            auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            val userId = auth.currentUserOrNull()?.id ?: throw Exception("Sign up successful but could not retrieve User ID.")

            val now = getCurrentIsoTimestamp()
            val userProfile = User(
                id = userId, username = username, firstName = firstName,
                lastName = lastName.ifBlank { null },
                email = email, phoneNumber = phoneNumber,
                timeCreated = now, timeUpdated = now
            )

            val driverProfile = DriverInsertDto(
                driverId = userId,
                studentCard = "BELUM_UPLOAD",
                driverStatus = "OFFLINE",
                driverTypeId = driverTypeId // SEKARANG UDAH NGGAK ERROR
            )

            postgrest.from("PROFILE").insert(userProfile)
            postgrest.from("DRIVER").insert(driverProfile)

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signIn(email: String, password: String): Result<Unit> {
        return try {
            auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signOut() {
        auth.signOut()
    }

    suspend fun getUserProfile(): Result<User?> {
        return try {
            val userId = auth.currentUserOrNull()?.id ?: return Result.success(null)
            val profile = postgrest.from("PROFILE").select {
                filter { eq("user_id", userId) }
            }.decodeSingleOrNull<User>()
            Result.success(profile)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateProfile(
        username: String, firstName: String, phoneNumber: String
    ): Result<Unit> {
        return try {
            val userId = auth.currentUserOrNull()?.id ?: throw Exception("User not logged in")
            val updateData = mapOf(
                "username" to username, "first_name" to firstName,
                "phone_number" to phoneNumber, "time_updated" to getCurrentIsoTimestamp()
            )
            postgrest.from("PROFILE").update(updateData) {
                filter { eq("user_id", userId) }
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getCurrentUserId(): String? {
        return auth.currentUserOrNull()?.id
    }
}