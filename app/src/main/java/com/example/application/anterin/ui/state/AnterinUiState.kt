package com.example.application.anterin.ui.state

import com.example.application.anterin.data.model.DrivingRoute
import com.example.application.anterin.data.model.HistoryLocation
import com.example.application.anterin.data.model.MapLocation
import com.example.application.anterin.data.model.VehicleType

data class AnterinUiState(
    val pickup: MapLocation? = null,
    val destination: MapLocation? = null,

    val selectedVehicleType: String? = null,
    val histories: List<HistoryLocation> = emptyList(),
    val vehicleTypes: List<VehicleType> = emptyList(),

    val route: DrivingRoute? = null
)
