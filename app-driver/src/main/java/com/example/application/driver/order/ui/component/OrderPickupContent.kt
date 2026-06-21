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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application._core.ui.component.ButtonBlue
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.BluePrimary
import com.example.application._core.ui.theme.WhiteSoft
import com.example.application.driver.order.data.dto.ActiveOrderDto

@Composable
fun OrderPickupContent(
    order: ActiveOrderDto,
    onConfirm: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(WhiteSoft)
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Picking Up Food",
                    color = BluePrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = "Jajan-In",
                    color = BlackSoft
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = order.storeName,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = order.pickupAddress,
            color = Color.DarkGray
        )

        Spacer(Modifier.height(24.dp))

        val priceDisplay = if (order.totalPrice != null) "Rp${order.totalPrice.toLong()}" else "Rp-"
        Text(
            text = priceDisplay,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 28.sp
        )

        Spacer(Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            ButtonBlue(
                text = "Confirm Pick Up",
                onClick = onConfirm,
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            )
        }
    }
}
