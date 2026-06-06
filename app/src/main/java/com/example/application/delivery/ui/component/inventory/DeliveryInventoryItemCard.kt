package com.example.application.delivery.ui.component.inventory

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
import com.example.application.global.ui.component.CircleButton
import com.example.application.global.ui.theme.GrayDark
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext

@Composable
fun DeliveryInventoryItemCard(
    name: String,
    price: String,
    imageUrl: String? = null,
    imageRes: Int,
    modifier: Modifier = Modifier,
    onAddToCart: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .width(160.dp)
            .border(
                width = 1.dp,
                color = GrayDark,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
    ) {

        val ctx = LocalContext.current

        Box {
            AsyncImage(
                model = ImageRequest.Builder(ctx)
                    .data(imageUrl ?: imageRes)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            Column() {
                Text(
                    text = name,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Text(
                    text = price,
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }

            CircleButton(
                text = "+",
                modifier = Modifier.size(20.dp),
                onClick = onAddToCart
            )
        }
    }
}
