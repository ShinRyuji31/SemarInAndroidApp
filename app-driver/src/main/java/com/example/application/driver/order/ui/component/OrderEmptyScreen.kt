package com.example.application.driver.order.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application._core.ui.theme.GrayMedium

@Composable
fun OrderEmptyScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GrayMedium)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Belum ada pesanan aktif",
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
