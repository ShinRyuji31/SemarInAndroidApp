package com.example.application.anterin.ui.component.destination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application._core.ui.theme.WhiteSoft

@Composable
fun AnterinLocationCard(
    pickup: String,
    destination: String
) {

    Column(
        modifier = Modifier
            .background(WhiteSoft)
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        AnterinLocationItem(
            title = "Pick Up",
            subtitle = pickup
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        AnterinLocationItem(
            title = "Drop Off",
            subtitle = destination
        )
    }
}