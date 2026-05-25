package com.example.application.global.data.location

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.global.data.location.model.LocationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private val locationService = LocationService(application)
    private val repository = LocationRepository(locationService)

    private val _uiState = MutableStateFlow(LocationUiState())
    val uiState: StateFlow<LocationUiState> = _uiState.asStateFlow()

    fun fetchLocation() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            val location = repository.fetchCurrentLocation()
            
            if (location != null) {
                _uiState.value = LocationUiState(
                    isLoading = false,
                    locationName = location.address,
                    error = null
                )
            } else {
                _uiState.value = LocationUiState(
                    isLoading = false,
                    locationName = "",
                    error = "Location unavailable"
                )
            }
        }
    }
}
