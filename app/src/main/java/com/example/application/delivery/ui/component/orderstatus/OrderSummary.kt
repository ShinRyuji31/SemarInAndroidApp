package com.example.application.delivery.ui.component.orderstatus

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.global.ui.theme.BlackSoft
import okio.blackholeSink

@Composable
fun OrderSummary(
    subtotal: String,
    deliveryFee: String,
    total: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        OrderSummaryRow(
            label = "Subtotal",
            value = subtotal
        )

        OrderSummaryRow(
            label = "Delivery fee",
            value = deliveryFee
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Text(
                text = "Total",
                fontSize = 18.sp,
                color = BlackSoft,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = total,
                fontSize = 18.sp,
                color = BlackSoft,
                fontWeight = FontWeight.Bold
            )
        }
    }
}