package com.example.application.anterin.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.application.anterin.data.model.DrivingRoute
import com.example.application.anterin.data.repository.AnterinRepository
import com.example.application.anterin.ui.state.AnterinUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AnterinViewModel : ViewModel() {

    private val repository = AnterinRepository()

    private val _uiState = MutableStateFlow(
        AnterinUiState(
            histories = repository.getHistories(),
            vehicleTypes = repository.getVehicleTypes()
        )
    )

    val uiState: StateFlow<AnterinUiState> = _uiState.asStateFlow()

    // ======================
    // INPUT HANDLER
    // ======================
    fun onPickupChange(value: String) {
        _uiState.update {
            it.copy(pickup = value)
        }
    }

    fun onDestinationChange(value: String) {
        _uiState.update {
            it.copy(destination = value)
        }
    }

    // ======================
    // VEHICLE
    // ======================
    fun selectVehicleType(id: String) {
        _uiState.update {
            it.copy(selectedVehicleType = id)
        }
    }

    // ======================
    // ROUTE CONFIRM
    // ======================
    fun confirmRoute() {
        val state = _uiState.value

        _uiState.update {
            it.copy(
                route = DrivingRoute(
                    pickup = state.pickup,
                    destination = state.destination,
                    distance = "5.2 km"
                )
            )
        }
    }
}