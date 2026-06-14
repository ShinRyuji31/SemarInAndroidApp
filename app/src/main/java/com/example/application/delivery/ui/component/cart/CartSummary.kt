package com.example.application.delivery.ui.component.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application._core.util.toRupiah

@Composable
fun CartSummary(
    subtotal: Int,
    deliveryFee: Int,
    total: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        HorizontalDivider(
            modifier = Modifier.padding(bottom = 16.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "Deliver to",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "Current Location",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Gg. Kutai Utara No. 1",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Subtotal", color = Color.Gray, fontSize = 15.sp)
            Text(text = subtotal.toRupiah(), color = Color.Gray, fontSize = 15.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Delivery fee", color = Color.Gray, fontSize = 15.sp)
            Text(text = deliveryFee.toRupiah(), color = Color.Gray, fontSize = 15.sp)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Total", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text(text = total.toRupiah(), fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}