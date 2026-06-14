package com.example.application.delivery.ui.component.store

import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.R
import com.example.application.delivery.data.model.Store
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.GrayDark
import com.example.application._core.ui.theme.GrayMedium
import com.example.application._core.ui.theme.WhiteSoft
import com.example.application._core.ui.theme.Yellow

@Composable
fun StoreCardLong(
    store: Store,
    onClick: () -> Unit = {}
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(WhiteSoft)
            .border(
                width = 1.dp,
                color = GrayMedium,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
    ) {

        val ctx = LocalContext.current

        AsyncImage(
            model = ImageRequest.Builder(ctx)
                .data(store.imageUrl ?: store.imageRes)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .width(110.dp)
                .fillMaxHeight()
                .clip(
                    RoundedCornerShape(
                        topStart = 20.dp,
                        bottomStart = 20.dp
                    )
                ),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .weight(1f)
                .fillMaxHeight()
        ) {

            Text(
                text = store.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = BlackSoft,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = store.address,
                fontSize = 12.sp,
                color = GrayDark,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = store.promo,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = BlackSoft,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Yellow
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = store.rating.toString(),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 14.sp,
                    color = BlackSoft
                )
            }
        }
    }
}