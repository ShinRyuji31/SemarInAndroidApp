package com.example.application.ui.component.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.application.ui.theme.BluePrimary

@Composable
fun CircleButton(text: String) {
    Box(
        modifier = Modifier
            .size(28.dp)
            .background(BluePrimary, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = Color.White)
    }
}