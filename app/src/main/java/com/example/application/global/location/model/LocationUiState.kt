package com.example.application.global.location.model

data class LocationUiState(
    val isLoading: Boolean = false,
    val locationName: String = "",
    val error: String? = null
)
