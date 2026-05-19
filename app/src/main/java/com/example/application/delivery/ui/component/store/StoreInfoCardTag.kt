package com.example.application.delivery.ui.component.store

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.global.ui.theme.GrayDark

@Composable
fun StoreInfoCardTag(
    text: String
) {
    Text(
        text = text,
        fontSize = 11.sp,
        color = Color(0xFF2D9CDB),

        modifier = Modifier
            .background(
                GrayDark,
                shape = RoundedCornerShape(50)
            )
            .padding(
                horizontal = 10.dp,
                vertical = 4.dp
            )
    )
}