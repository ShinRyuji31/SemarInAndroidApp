package com.example.application.profile.ui.component.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileInfo(
    name: String,
    phone: String,
    onEditClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = phone,
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Edit Profile",
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier.clickable(onClick = onEditClick)
        )
    }
}