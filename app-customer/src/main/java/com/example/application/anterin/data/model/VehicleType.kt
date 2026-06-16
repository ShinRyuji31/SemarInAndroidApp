package com.example.application.anterin.data.model

import kotlinx.serialization.Serializable

@Serializable
data class VehicleType(
    val id: String,
    val name: String,
    val capacity: Int,
    val price: Int,
    val icon: Int
)
