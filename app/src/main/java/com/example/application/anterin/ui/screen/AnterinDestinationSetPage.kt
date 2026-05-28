package com.example.application.anterin.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.anterin.ui.component.destination.AnterinLocationCard
import com.example.application.anterin.ui.component.destination.AnterinVehicleItem
import com.example.application.global.ui.component.ButtonBlue
import com.example.application.global.ui.component.Header
import com.example.application.global.ui.component.MapWebView
import com.example.application.global.ui.theme.BluePrimary
import com.example.application.anterin.ui.viewmodel.AnterinViewModel
import com.example.application.global.data.location.LocationViewModel
import com.example.application.global.util.toRupiah

@Composable
fun AnterinDestinationSetPage(
    onBack: () -> Unit,
    onFindDriver: () -> Unit,
    viewModel: AnterinViewModel,
    locationViewModel: LocationViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val locationState by locationViewModel.uiState.collectAsState()
    
    val destination = uiState.destination?.address ?: "Unknown destination"
    val pickup = uiState.pickup?.address ?: "Unknown pickup"

    val userLatLng = if (locationState.latitude != null && locationState.longitude != null) {
        Pair(locationState.latitude!!, locationState.longitude!!)
    } else null

    Box(modifier = Modifier.fillMaxSize()) {
        
        // 1. REAL Interactive Map Preview (Disabled interactions)
        MapWebView(
            userLocation = userLatLng,
            pickupLocation = uiState.pickup,
            destinationLocation = uiState.destination,
            routeGeoJson = uiState.route?.geoJson,
            onMapClick = { _, _ -> }, // Disable selection on this page
            isInteractionEnabled = false,
            modifier = Modifier.fillMaxSize()
        )

        Header(
            title = "Anter-In",
            onBack = onBack
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(BluePrimary)
        ) {
            Column {
                AnterinLocationCard(
                    pickup = pickup,
                    destination = destination
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column {
                    uiState.vehicleTypes.forEach { vehicle ->
                        // Calculate real price
                        val distance = uiState.route?.distanceKm ?: 0.0
                        val totalPrice = (vehicle.price.toDouble() * distance).toLong()

                        AnterinVehicleItem(
                            name = vehicle.name,
                            capacity = "${vehicle.capacity} person",
                            price = totalPrice.toRupiah(), // Real calculated price
                            icon = vehicle.icon,
                            isSelected = uiState.selectedVehicleType == vehicle.id,
                            onClick = {
                                viewModel.selectVehicleType(vehicle.id)
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .navigationBarsPadding()
                ) {
                    ButtonBlue(
                        text = "Find Driver",
                        onClick = onFindDriver,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                            .fillMaxWidth()
                            .height(50.dp),
                        enabled = uiState.selectedVehicleType != null
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}
