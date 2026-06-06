package com.example.application.delivery.ui.component.cart

import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CartItemComponent(
    name: String,
    price: String,
    imageUrl: String? = null,
    imageRes: Int,
    quantity: Int,

    onIncrease: () -> Unit = {},
    onDecrease: () -> Unit = {},

    showQuantitySelector: Boolean = true
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(
                1.dp,
                Color.LightGray.copy(alpha = 0.5f),
                RoundedCornerShape(12.dp)
            )
            .background(
                Color.White,
                RoundedCornerShape(12.dp)
            )
            .padding(12.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {

        val ctx = LocalContext.current

        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Transparent)
        ) {

            AsyncImage(
                model = ImageRequest.Builder(ctx)
                    .data(imageUrl ?: imageRes)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = price,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        if (showQuantitySelector) {

            CartQuantitySelector(
                quantity = quantity,
                onIncrease = onIncrease,
                onDecrease = onDecrease
            )

        } else {

            Text(
                text = "${quantity}x",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}
