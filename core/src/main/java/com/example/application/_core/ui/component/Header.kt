package com.example.application._core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application._core.ui.theme.WhiteSoft
import com.example.application._core.ui.theme.blueBlueGradient

@Composable
fun Header(
    title: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(WhiteSoft)
                .statusBarsPadding()
        )

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(blueBlueGradient())
        ) {

            BackButton(
                onClick = onBack,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterStart)
            )

            Text(
                text = title,
                color = WhiteSoft,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}