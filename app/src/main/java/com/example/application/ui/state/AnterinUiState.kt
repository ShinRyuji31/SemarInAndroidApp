package com.example.application.ui.state

import com.example.application.data.model.anterin.DrivingRoute
import com.example.application.data.model.anterin.HistoryLocation
import com.example.application.data.model.anterin.VehicleType

data class AnterinUiState(
    val pickup: String = "",
    val destination: String = "",

    val selectedVehicleType: String? = null,
    val histories: List<HistoryLocation> = emptyList(),
    val vehicleTypes: List<VehicleType> = emptyList(),

    val route: DrivingRoute? = null
)