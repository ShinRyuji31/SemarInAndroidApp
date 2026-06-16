package com.example.application._core.data.maps.repository

import com.example.application._core.data.maps.model.NominatimResponse
import com.example.application._core.data.maps.model.OsrmResponse
import com.example.application._core.data.maps.model.Route
import com.example.application._core.data.maps.model.SearchLocationResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header

class MapsRepository(private val client: HttpClient) {

    suspend fun reverseGeocode(lat: Double, lon: Double): String? {
        return try {
            val response: NominatimResponse = client.get("https://nominatim.openstreetmap.org/reverse") {
                url {
                    parameters.append("lat", lat.toString())
                    parameters.append("lon", lon.toString())
                    parameters.append("format", "json")
                }
                header("User-Agent", "SemarinAndroidApp")
            }.body()
            
            response.display_name
        } catch (e: Exception) {
            null
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

    suspend fun searchLocation(query: String): Result<List<SearchLocationResult>> {
        return try {
            val response: List<SearchLocationResult> = client.get("https://nominatim.openstreetmap.org/search") {
                url {
                    parameters.append("q", query)
                    parameters.append("format", "jsonv2")
                    parameters.append("limit", "5")
                }
                header("User-Agent", "SemarinAndroidApp")
            }.body()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
