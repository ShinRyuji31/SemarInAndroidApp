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
import com.example.application._core.ui.theme.BluePrimary
import com.example.application._core.ui.theme.GrayDark
import com.example.application._core.ui.theme.GrayMedium
import com.example.application._core.ui.theme.WhiteSoft

@Composable
fun StoreInfoCardTag(
    text: String
) {
    Text(
        text = text,
        fontSize = 12.sp,
        color = WhiteSoft,

        modifier = Modifier
            .background(
                BluePrimary,
                shape = RoundedCornerShape(50)
            )
            .padding(
                horizontal = 10.dp,
                vertical = 4.dp
            )
    )
}