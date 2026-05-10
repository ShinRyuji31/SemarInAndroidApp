package com.example.application.ui.screen.anterin

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.ui.component.anterin.AnterinBackground
import com.example.application.ui.component.anterin.AnterinFormCard
import com.example.application.ui.component.global.Header
import com.example.application.viewmodel.AnterinViewModel

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

    var pickup by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }

    val histories by viewModel.histories.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        AnterinBackground()

        Header(
            title = "Anter-In",
            onBack = onBack
        )

        AnterinFormCard(
            mode = mode,
            pickup = pickup,
            destination = destination,
            histories = histories,
            onPickupClick = onPickupClick,
            onDestinationClick = onDestinationClick,
            onPickupChange = { pickup = it },
            onDestinationChange = { destination = it },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 16.dp)
                .offset(y = 40.dp)
        )
    }
}