package com.example.application.driver._core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DriverDto(
    @SerialName("driver_id") val driverId: String,
    @SerialName("driver_status") val driverStatus: String
)