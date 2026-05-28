package com.example.application.anterin.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.anterin.data.model.DrivingRoute
import com.example.application.anterin.data.model.MapLocation
import com.example.application.anterin.data.model.SearchLocationResult
import com.example.application.anterin.data.repository.AnterinRepository
import com.example.application.anterin.data.repository.GeocodingRepository
import com.example.application.anterin.data.repository.OsrmRepository
import com.example.application.anterin.data.repository.SearchLocationRepository
import com.example.application.anterin.ui.state.AnterinUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AnterinViewModel : ViewModel() {

    private val repository = AnterinRepository()
    private val geocodingRepository = GeocodingRepository()
    private val osrmRepository = OsrmRepository()
    private val searchRepository = SearchLocationRepository()

    private val _uiState = MutableStateFlow(
        AnterinUiState(
            histories = repository.getHistories(),
            vehicleTypes = repository.getVehicleTypes()
        )
    )
    val uiState: StateFlow<AnterinUiState> = _uiState.asStateFlow()

    private val _searchSuggestions = MutableStateFlow<List<SearchLocationResult>>(emptyList())
    val searchSuggestions: StateFlow<List<SearchLocationResult>> = _searchSuggestions.asStateFlow()

    private var geocodeJob: Job? = null
    private var searchJob: Job? = null

    fun searchLocations(query: String) {
        searchJob?.cancel()
        if (query.length < 3) {
            _searchSuggestions.value = emptyList()
            return
        }
        
        searchJob = viewModelScope.launch {
            delay(500)
            searchRepository.searchLocation(query).onSuccess { results ->
                _searchSuggestions.value = results
            }.onFailure {
                _searchSuggestions.value = emptyList()
            }
        }
    }

    fun selectSuggestion(result: SearchLocationResult, isPickup: Boolean) {
        val lat = result.lat.toDoubleOrNull() ?: return
        val lng = result.lon.toDoubleOrNull() ?: return
        val loc = MapLocation(lat, lng, result.displayName)
        
        _uiState.update { 
            if (isPickup) it.copy(pickup = loc) else it.copy(destination = loc)
        }
        _searchSuggestions.value = emptyList()
        updateRoute()
    }

    fun updatePickupByCoordinates(lat: Double, lng: Double) {
        geocodeJob?.cancel()
        geocodeJob = viewModelScope.launch {
            _uiState.update { it.copy(pickup = MapLocation(lat, lng, "Resolving address...")) }
            
            val address = geocodingRepository.reverseGeocode(lat, lng) ?: "Unknown Location"
            _uiState.update {
                it.copy(pickup = MapLocation(lat, lng, address))
            }
            updateRoute()
        }
    }

    fun updateDestinationByCoordinates(lat: Double, lng: Double) {
        geocodeJob?.cancel()
        geocodeJob = viewModelScope.launch {
            _uiState.update { it.copy(destination = MapLocation(lat, lng, "Resolving address...")) }
            
            val address = geocodingRepository.reverseGeocode(lat, lng) ?: "Unknown Location"
            _uiState.update {
                it.copy(destination = MapLocation(lat, lng, address))
            }
            updateRoute()
        }
    }

    private fun updateRoute() {
        val state = _uiState.value
        if (state.pickup != null && state.destination != null) {
            viewModelScope.launch {
                osrmRepository.getRoute(
                    state.pickup.latitude, state.pickup.longitude,
                    state.destination.latitude, state.destination.longitude
                ).onSuccess { route ->
                    if (route != null) {
                        try {
                            val geoJsonString = Json.encodeToString(route.geometry)
                            _uiState.update {
                                it.copy(
                                    route = DrivingRoute(
                                        pickupAddress = state.pickup.address,
                                        destinationAddress = state.destination.address,
                                        distanceKm = route.distance / 1000.0,
                                        durationMinutes = (route.duration / 60.0).toInt(),
                                        geoJson = geoJsonString
                                    )
                                )
                            }
                        } catch (e: Exception) {
                            Log.e("AnterinViewModel", "Error encoding route geometry", e)
                        }
                    }
                }.onFailure { e ->
                    Log.e("AnterinViewModel", "Route fetching failed", e)
                }
            }
        }
    }

    fun selectVehicleType(id: String) {
        _uiState.update {
            it.copy(selectedVehicleType = id)
        }
    }
}
