package com.example.application.global.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import com.example.application.global.location.model.UserLocation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.tasks.await
import java.util.Locale

class LocationService(private val context: Context) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(): UserLocation? {
        return try {
            val location: Location? = fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                null
            ).await()

            location?.let {
                val address = getAddressFromLocation(it.latitude, it.longitude)
                UserLocation(
                    latitude = it.latitude,
                    longitude = it.longitude,
                    address = address ?: "Unknown Address"
                )
            }
        } catch (e: Exception) {
            null
        }
    }

    private fun getAddressFromLocation(lat: Double, lon: Double): String? {
        return try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(lat, lon, 1)
            if (!addresses.isNullOrEmpty()) {
                val address = addresses[0]
                val street = address.thoroughfare ?: address.featureName ?: ""
                val city = address.locality ?: address.subAdminArea ?: ""
                if (street.isNotEmpty() && city.isNotEmpty()) "$street, $city"
                else if (city.isNotEmpty()) city
                else address.getAddressLine(0)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
