package com.example.application.anterin.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.R
import com.example.application.global.ui.component.Header
import com.example.application.global.ui.theme.BluePrimary
import com.example.application.global.ui.theme.WhiteSoft

enum class DriverState {
    FINDING,
    FOUND
}

@Composable
fun AnterinFindingDriverPage(
    state: DriverState,
    onBack: () -> Unit
) {

    val (title, subtitle, image) = when (state) {
        DriverState.FINDING -> Triple(
            "Hold on tight.",
            "We are finding a driver for you",
            R.drawable.logo_coloredbike
        )

        DriverState.FOUND -> Triple(
            "Horay!",
            "We have found you a driver",
            R.drawable.ic_star
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BluePrimary)
    ) {

        Header(
            title = "Anter-In",
            onBack = onBack
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = WhiteSoft
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = WhiteSoft
            )

            Spacer(modifier = Modifier.height(32.dp))

            Icon(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(120.dp),
                tint = Color.Unspecified
            )
        }
    }
}