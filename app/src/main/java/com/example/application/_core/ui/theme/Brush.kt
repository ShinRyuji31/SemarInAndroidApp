package com.example.application._core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush

@Composable
fun blueWhiteGradient() = Brush.verticalGradient(
    listOf(
        BluePrimary,
        WhiteSoft
    )
)