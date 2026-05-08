package com.example.application.ui.component.shared.delivery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.data.model.Restaurant

@Composable
fun StoreInfoCard(
    restaurant: Restaurant
){
    Box(
        modifier = Modifier
            .offset(y = (-20).dp)
            .padding(horizontal = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {

        Column {

            Text(
                (restaurant.name),
                fontSize = 18.sp
            )

            Text(
                (restaurant.address),
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text("Opening Hours:", fontSize = 12.sp, fontWeight = Bold)
            Text(
                "${restaurant.openTime} - ${restaurant.closeTime} WIB (${restaurant.openDays})",
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "⭐ ${restaurant.rating}",
                    fontSize = 12.sp
                )

                restaurant.tags.forEach { tag ->

                    StoreTag(
                        text = tag
                    )
                }
            }
        }
    }
}