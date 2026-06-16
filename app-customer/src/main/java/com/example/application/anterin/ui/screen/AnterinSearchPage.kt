package com.example.application.anterin.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.application._core.ui.component.Header
import com.example.application._core.ui.component.ButtonBlue
import com.example.application._core.ui.component.MapWebView
import com.example.application.anterin.ui.viewmodel.AnterinViewModel
import com.example.application._core.data.location.LocationViewModel
import com.example.application._core.ui.component.SearchBar
import org.koin.androidx.compose.koinViewModel

enum class MapMode {
    PICKUP,
    DESTINATION
}

@Composable
fun AnterinSearchPage(
    mode: MapMode,
    onConfirm: () -> Unit,
    onBack: () -> Unit,
    viewModel: AnterinViewModel,
    locationViewModel: LocationViewModel = koinViewModel()
) {
    val isPickup = mode == MapMode.PICKUP
    val uiState by viewModel.uiState.collectAsState()
    val suggestions by viewModel.searchSuggestions.collectAsState()

    var searchQuery by remember { mutableStateOf("") }

    val locationState by locationViewModel.uiState.collectAsState()
    val userLatLng = if (locationState.latitude != null && locationState.longitude != null) {
        Pair(locationState.latitude!!, locationState.longitude!!)
    } else null

    LaunchedEffect(Unit) {
        locationViewModel.fetchLocation()
    }

    Box(modifier = Modifier.fillMaxSize()) {

        MapWebView(
            userLocation = userLatLng,
            pickupLocation = uiState.pickup,
            destinationLocation = uiState.destination,
            routeGeoJson = uiState.route?.geoJson,
            onMapClick = { lat, lng ->
                if (isPickup) {
                    viewModel.updatePickupByCoordinates(lat, lng)
                } else {
                    viewModel.updateDestinationByCoordinates(lat, lng)
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f)
        ) {
            Header(
                title = if (isPickup) "Select Pick-up" else "Select Destination",
                onBack = onBack
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(modifier = Modifier.padding(horizontal = 8.dp)) {
                Column {
                    SearchBar(
                        value = searchQuery,
                        onValueChange = {
                            searchQuery = it
                            viewModel.searchLocations(it)
                        },
                        placeholderText = if (isPickup) "Search pickup location" else "Search destination"
                    )

                    if (suggestions.isNotEmpty()) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                        ) {
                            LazyColumn(modifier = Modifier.heightIn(max = 250.dp)) {
                                items(suggestions) { suggestion ->
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                viewModel.selectSuggestion(suggestion, isPickup)
                                                searchQuery = ""
                                            }
                                            .padding(16.dp)
                                    ) {
                                        Text(text = suggestion.displayName, fontSize = 14.sp)
                                    }
                                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                                }
                            }
                        }
                    }
                }
            }
        }

        // Action Button
        ButtonBlue(
            text = when {
                isPickup && uiState.pickup?.address == "Resolving address..." -> "Resolving address..."
                isPickup && uiState.pickup == null -> "Choose on Map"
                isPickup -> "Set Pick-up Location"
                !isPickup && uiState.destination?.address == "Resolving address..." -> "Resolving address..."
                !isPickup && uiState.destination == null -> "Choose on Map"
                else -> "Set Destination Location"
            },
            onClick = {
                if (isPickup) {
                    if (uiState.pickup != null) {
                        onConfirm()
                    }
                } else {
                    if (uiState.destination != null) {
                        onConfirm()
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(16.dp)
                .fillMaxWidth()
                .height(50.dp),
            enabled = if (isPickup) {
                uiState.pickup != null && uiState.pickup?.address != "Resolving address..."
            } else {
                uiState.destination != null && uiState.destination?.address != "Resolving address..."
            }
        )
    }
}
