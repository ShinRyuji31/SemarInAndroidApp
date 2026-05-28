package com.example.application.anterin.data.repository

import io.ktor.client.HttpClient
import io.github.jan.supabase.postgrest.postgrest
import com.example.application.global.data.remote.SupabaseClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class NominatimResponse(
    val display_name: String
)

class GeocodingRepository {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            })
        }
    }

    suspend fun reverseGeocode(lat: Double, lon: Double): String? {
        return try {
            val response: NominatimResponse = client.get("https://nominatim.openstreetmap.org/reverse") {
                url {
                    parameters.append("lat", lat.toString())
                    parameters.append("lon", lon.toString())
                    parameters.append("format", "json")
                }
                // Nominatim requires a User-Agent
                header("User-Agent", "SemarinAndroidApp")
            }.body()
            
            response.display_name
        } catch (e: Exception) {
            null
        }
    }
}
