package com.example.application.anterin.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement

@Serializable
data class OsrmResponse(
    val routes: List<Route>
)

@Serializable
data class Route(
    val distance: Double,
    val duration: Double,
    val geometry: JsonObject
)

class OsrmRepository {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            })
        }
    }

    suspend fun getRoute(
        startLat: Double, startLon: Double,
        endLat: Double, endLon: Double
    ): Result<Route?> {
        return try {
            val url = "https://router.project-osrm.org/route/v1/driving/$startLon,$startLat;$endLon,$endLat?overview=full&geometries=geojson"
            val response: OsrmResponse = client.get(url).body()
            Result.success(response.routes.firstOrNull())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
