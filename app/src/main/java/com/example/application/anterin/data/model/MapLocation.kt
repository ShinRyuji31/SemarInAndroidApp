package com.example.application.anterin.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MapLocation(
    val latitude: Double,
    val longitude: Double,
    val address: String
)
