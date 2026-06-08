package com.example.application._core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.application._core.ui.theme.GrayDark
import com.example.application._core.ui.theme.WhiteSoft

@Composable
fun ButtonSocial(icon: Int) {
    Box(
        modifier = Modifier
            .size(width = 70.dp, height = 60.dp)
            .background(
                color = WhiteSoft,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = GrayDark,
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(30.dp),
            tint = Color.Unspecified
        )
    }
}