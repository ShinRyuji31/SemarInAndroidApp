package com.example.application.global.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush

@Composable
fun blueWhiteGradient() = Brush.verticalGradient(
    listOf(
        BluePrimary,
        WhiteSoft
    )
)