package com.example.application.ui.component.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.data.model.PromoBanner

@Composable
fun DashboardTopBanner(
    banners: List<PromoBanner>
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {

        Text(
            text = "Semar Promo Cek!!",

            fontSize = 20.sp,

            fontWeight = FontWeight.Bold,

            color = Color.Black,

            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),

            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            items(banners) { banner ->

                Image(
                    painter = painterResource(id = banner.imageRes),

                    contentDescription = "Banner Promo",

                    contentScale = ContentScale.Crop,

                    modifier = Modifier
                        .size(width = 280.dp, height = 160.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
        }
    }
}