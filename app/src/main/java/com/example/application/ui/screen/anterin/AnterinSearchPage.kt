package com.example.application.ui.screen.anterin

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application.ui.component.anterin.AnterinBackground
import com.example.application.ui.component.global.Header
import com.example.application.ui.component.global.ButtonBlue
import com.example.application.ui.component.global.SearchBar
import com.example.application.ui.navigation.Routes

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