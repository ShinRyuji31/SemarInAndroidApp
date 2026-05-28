package com.example.application.anterin.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.anterin.ui.component.AnterinBackground
import com.example.application.anterin.ui.component.AnterinFormCard
import com.example.application.global.ui.component.Header
import com.example.application.anterin.ui.viewmodel.AnterinViewModel

enum class MainMode {
    PICKUP_ONLY,
    PICKUP_AND_DESTINATION
}

@Composable
fun AnterinMainPage(
    mode: MainMode,
    onPickupClick: () -> Unit,
    onDestinationClick: () -> Unit,
    onBack: () -> Unit,
    viewModel: AnterinViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        AnterinBackground()

        Header(
            title = "Anter-In",
            onBack = onBack
        )

        AnterinFormCard(
            mode = mode,
            pickup = uiState.pickup,
            destination = uiState.destination,
            histories = uiState.histories,
            onPickupClick = onPickupClick,
            onDestinationClick = onDestinationClick,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 16.dp)
                .offset(y = 40.dp)
        )
    }
}
