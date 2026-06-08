package com.example.application.dashboard.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.R
import com.example.application.auth.data.model.User
import com.example.application._core.data.location.model.LocationUiState
import com.example.application._core.ui.theme.WhiteSoft

@Composable
fun DashboardHeader(
    user: User,
    locationState: LocationUiState
) {
    val displayText = when {
        locationState.isLoading -> "Getting location..."
        locationState.error != null -> locationState.error
        locationState.locationName.isNotEmpty() -> locationState.locationName
        else -> "Set your location"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = null,
                modifier = Modifier.size(28.dp),
                tint = WhiteSoft
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = displayText,
                color = WhiteSoft,
                fontSize = 14.sp,
                maxLines = 1
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = null,
            modifier = Modifier.size(28.dp),
            tint = WhiteSoft
        )
    }
}
