package com.example.application.driver.order.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application._core.ui.component.ButtonBlue
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.BluePrimary
import com.example.application._core.ui.theme.GrayDark
import com.example.application._core.ui.theme.WhiteSoft

@Composable
fun OrderActionContent(
    title: String,
    locationName: String,
    address: String,
    buttonText: String,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(WhiteSoft)
            .padding(20.dp)
    ) {
        Text(
            text = title,
            color = BluePrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = locationName,
            fontWeight = FontWeight.Bold,
            color = BlackSoft,
            fontSize = 16.sp
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = address,
            color = GrayDark,
            fontSize = 14.sp
        )

        Spacer(Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            ButtonBlue(
                text = buttonText,
                onClick = onButtonClick,
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            )
        }
    }
}