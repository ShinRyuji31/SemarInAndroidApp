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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

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
        email: String, 
        password: String, 
        username: String, 
        firstName: String, 
        lastName: String, 
        phoneNumber: String
    ): Result<Unit> {
        return try {
            Log.d("UserRepository", "Starting Supabase Auth sign up for: $email")
            
            // 1. Sign up to Supabase Auth
            auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            
            // 2. Retrieve the new User ID from the session
            val userId = auth.currentUserOrNull()?.id 
                ?: throw Exception("Sign up successful but could not retrieve User ID. Ensure 'Confirm Email' is DISABLED in Supabase.")

            Log.d("UserRepository", "Auth successful. User ID: $userId. Proceeding to profile insertion...")

            val now = getCurrentIsoTimestamp()

            // 3. Prepare profile metadata (matching 'PROFILE' table schema)
            val userProfile = User(
                id = userId,
                username = username,
                firstName = firstName,
                lastName = if (lastName.isBlank()) null else lastName,
                email = email,
                phoneNumber = phoneNumber,
                timeCreated = now,
                timeUpdated = now
            )
            
            val customerProfile = Customer(customerId = userId)
            
            // 4. Insert metadata into 'PROFILE' table
            Log.d("UserRepository", "Inserting into 'PROFILE' table...")
            postgrest.from("PROFILE").insert(userProfile)
            
            // 5. Insert record into 'CUSTOMER' table
            Log.d("UserRepository", "Inserting into 'CUSTOMER' table...")
            postgrest.from("CUSTOMER").insert(customerProfile)
            
            Log.d("UserRepository", "Registration flow completed successfully.")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("UserRepository", "Sign up flow failed: ${e.localizedMessage}", e)
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
            
            // Fetch from 'PROFILE' table
            val profile = postgrest.from("PROFILE").select {
                filter {
                    eq("user_id", userId)
                }
            }.decodeSingleOrNull<User>()
            
            Result.success(profile)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateProfile(
        username: String,
        firstName: String,
        phoneNumber: String
    ): Result<Unit> {
        return try {

            val userId = auth.currentUserOrNull()?.id
                ?: throw Exception("User not logged in")

            val updateData = mapOf(
                "username" to username,
                "first_name" to firstName,
                "phone_number" to phoneNumber,
                "time_updated" to getCurrentIsoTimestamp()
            )

            postgrest
                .from("PROFILE")
                .update(updateData) {
                    filter {
                        eq("user_id", userId)
                    }
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