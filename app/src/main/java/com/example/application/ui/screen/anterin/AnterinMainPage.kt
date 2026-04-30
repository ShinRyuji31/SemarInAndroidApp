package com.example.application.ui.screen.anterin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.application.ui.component.Anterin.AnterinBackground
import com.example.application.ui.component.anterin.AnterinHistoryItem
import com.example.application.ui.component.anterin.AnterinInputSection
import com.example.application.ui.component.global.Header
import com.example.application.ui.theme.WhiteSoft

enum class MainMode {
    PICKUP_ONLY,
    PICKUP_AND_DESTINATION
}

@Composable
fun AnterinMainPage(
    mode: MainMode,
    onPickupClick: () -> Unit,
    onDestinationClick: () -> Unit,
    onBack: () -> Unit
) {

    var pickup by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {

        AnterinBackground()

        Header(
            title = "Anter-In",
            onBack = onBack
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 16.dp)
                .offset(y = 40.dp)
                .background(
                    WhiteSoft,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(16.dp)
        ) {

            Column {

                AnterinInputSection(
                    title = "Pick Up",
                    value = pickup,
                    placeholder = "Current Location",
                    onClick = onPickupClick,
                    onValueChange = { pickup = it }
                )

                if (mode == MainMode.PICKUP_AND_DESTINATION) {

                    Spacer(modifier = Modifier.height(12.dp))

                    AnterinInputSection(
                        title = "Drop Off",
                        value = destination,
                        placeholder = "Choose Destination",
                        onClick = onDestinationClick,
                        onValueChange = { destination = it }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text("History", fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(8.dp))

                AnterinHistoryItem("Gg. Kutai Utara No. 1, Sumber")
                AnterinHistoryItem("Faculty of Law UNS, Jl. Ir. Sutami No.36A")
                AnterinHistoryItem("Lokananta Bloc, Kerten")
            }
        }
    }
}

