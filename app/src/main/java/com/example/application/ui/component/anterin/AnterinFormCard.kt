package com.example.application.ui.component.anterin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.application.data.model.anterin.HistoryLocation
import com.example.application.ui.screen.anterin.MainMode
import com.example.application.ui.theme.WhiteSoft

@Composable
fun AnterinFormCard(
    mode: MainMode,
    pickup: String,
    destination: String,
    histories: List<HistoryLocation>,
    onPickupClick: () -> Unit,
    onDestinationClick: () -> Unit,
    onPickupChange: (String) -> Unit,
    onDestinationChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .background(
                WhiteSoft,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp)
    ) {

        AnterinInputSection(
            title = "Pick Up",
            value = pickup,
            placeholder = "Current Location",
            onClick = onPickupClick,
            onValueChange = onPickupChange
        )

        if (mode == MainMode.PICKUP_AND_DESTINATION) {

            Spacer(modifier = Modifier.height(12.dp))

            AnterinInputSection(
                title = "Drop Off",
                value = destination,
                placeholder = "Choose Destination",
                onClick = onDestinationClick,
                onValueChange = onDestinationChange
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "History",
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        histories.forEach { history ->
            AnterinHistoryItem(history.address)
        }
    }
}