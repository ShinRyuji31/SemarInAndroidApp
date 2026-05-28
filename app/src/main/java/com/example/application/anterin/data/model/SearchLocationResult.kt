package com.example.application.anterin.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchLocationResult(
    @SerialName("display_name")
    val displayName: String,
    val lat: String,
    val lon: String
)
