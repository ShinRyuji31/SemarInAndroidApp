package com.example.application._core.data.location.model

data class LocationUiState(
    val isLoading: Boolean = false,
    val locationName: String = "",
    val latitude: Double? = null,
    val longitude: Double? = null,
    val error: String? = null
)
