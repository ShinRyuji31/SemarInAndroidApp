package com.example.application.ui.screen.anterin

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.ui.component.anterin.AnterinBackground
import com.example.application.ui.component.global.Header
import com.example.application.ui.component.global.ButtonBlue
import com.example.application.ui.component.global.SearchBar
import com.example.application.ui.navigation.Routes
import com.example.application.viewmodel.AnterinViewModel

enum class MapMode {
    PICKUP,
    DESTINATION
}

@Composable
fun AnterinSearchPage(
    mode: MapMode,
    onNavigate: (Routes) -> Unit,
    onBack: () -> Unit
) {

    val isPickup = mode == MapMode.PICKUP
    val viewModel: AnterinViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        AnterinBackground()

        Column {
            Header(
                title = "Anter-In",
                onBack = onBack
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 110.dp)
        ) {
            SearchBar(
                value = if (mode == MapMode.PICKUP)
                    uiState.pickup
                else
                    uiState.destination,

                onValueChange = {
                    if (mode == MapMode.PICKUP)
                        viewModel.onPickupChange(it)
                    else
                        viewModel.onDestinationChange(it)
                },

                placeholderText = if (mode == MapMode.PICKUP)
                    "Search pick-up location"
                else
                    "Search destination"
            )
        }

        ButtonBlue(
            text = if (isPickup) "Search pick-up location" else "Search destination",
            onClick = {
                if (mode == MapMode.PICKUP) {
                    onNavigate(Routes.AnterDestinationInputRoute)
                } else {
                    onNavigate(Routes.AnterDestinationSetRoute)
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(16.dp)
                .fillMaxWidth()
                .height(50.dp)
        )
    }
}