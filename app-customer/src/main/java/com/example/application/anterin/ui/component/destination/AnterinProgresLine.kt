package com.example.application.anterin.ui.component.destination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.GrayMedium

@Composable
fun AnterinProgresLine() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp),

        horizontalArrangement =
            Arrangement.spacedBy(8.dp)
    ) {

        repeat(5) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(
                        BlackSoft,
                        RoundedCornerShape(2.dp)
                    )
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(
                    GrayMedium,
                    RoundedCornerShape(2.dp)
                )
        )
    }
}