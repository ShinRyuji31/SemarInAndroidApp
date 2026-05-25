package com.example.application.global.location

import com.example.application.global.location.model.UserLocation

class LocationRepository(private val locationService: LocationService) {

    suspend fun fetchCurrentLocation(): UserLocation? {
        return locationService.getCurrentLocation()
    }
}
