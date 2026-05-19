package com.example.application.global.ui.component

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import com.example.application.R
import com.example.application.global.ui.theme.Yellow

@Composable
fun RatingStar(
    modifier: Modifier = Modifier,
    tint: Color = Yellow
) {

    Icon(
        painter = painterResource(id = R.drawable.ic_star),
        contentDescription = "Rating Star",
        modifier = modifier.size(8.dp),
        tint = tint
    )
}