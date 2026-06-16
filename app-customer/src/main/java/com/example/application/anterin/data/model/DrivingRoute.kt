package com.example.application.anterin.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DrivingRoute(
    val pickupAddress: String,
    val destinationAddress: String,
    val distanceKm: Double,
    val durationMinutes: Int,
    val geoJson: String? = null
)
