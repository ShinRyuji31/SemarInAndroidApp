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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.application._core.ui.component.CircleButton
import com.example.application._core.ui.theme.GrayDark
import com.example.application._core.ui.theme.GrayMedium

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
            .width(150.dp)
            .height(240.dp)
            .border(
                width = 1.dp,
                color = GrayMedium,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
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
                    .height(140.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(end = 36.dp)
            ) {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = price,
                    fontSize = 12.sp,
                    color = GrayDark
                )
            }
            CircleButton(
                text = "+",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.BottomEnd),
                onClick = onAddToCart
            )
        }
    }
}