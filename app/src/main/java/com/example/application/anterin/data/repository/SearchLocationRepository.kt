package com.example.application.anterin.data.repository

import com.example.application.anterin.data.model.SearchLocationResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class SearchLocationRepository {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            })
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
