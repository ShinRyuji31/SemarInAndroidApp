package com.example.application.delivery.ui.component.store

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.application.R
import com.example.application.delivery.data.model.Store
import com.example.application._core.ui.component.RatingStar
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.GrayMedium

@Composable
fun StoreCardShort(
    store: Store,
    modifier: Modifier = Modifier,
    onClick: (Store) -> Unit
) {

    Card(
        modifier = modifier
            .width(160.dp)
            .height(200.dp)
            .clickable {
                onClick(store)
            }
            .border(
                1.dp,
                GrayMedium,
                RoundedCornerShape(16.dp)
            ),

        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),

        elevation = CardDefaults.cardElevation(0.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(store.imageUrl ?: store.imageRes ?: R.drawable.dummy)
                    .crossfade(true)
                    .build(),

                placeholder = painterResource(R.drawable.dummy),
                error = painterResource(R.drawable.dummy),

                contentDescription = store.name,

                contentScale = ContentScale.Crop,

                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(10.dp)
            ) {

                Text(
                    text = store.name,
                    fontWeight = FontWeight.Bold,
                    color = BlackSoft,
                    fontSize = 14.sp,
                    maxLines = 2
                )

                Spacer(
                    Modifier.weight(1f)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RatingStar()

                    Spacer(
                        Modifier.width(4.dp)
                    )

                    Text(
                        text = store.rating.toString(),
                        fontSize = 12.sp,
                        color = BlackSoft,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}