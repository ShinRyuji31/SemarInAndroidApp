package com.example.application._core.data.maps.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class NominatimResponse(
    val display_name: String
)

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

@Serializable
data class SearchLocationResult(
    @SerialName("display_name")
    val displayName: String,
    val lat: String,
    val lon: String
)

@Serializable
data class MapLocation(
    val latitude: Double,
    val longitude: Double,
    val address: String
)
