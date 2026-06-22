package com.example.application.order.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DriverDto(
    @SerialName("driver_id") val driverId: String,
    @SerialName("driver_status") val driverStatus: String
)

@Serializable
data class DriverLocationDto(
    @SerialName("driver_id") val driverId: String,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("time_updated") val timeUpdated: String
)
