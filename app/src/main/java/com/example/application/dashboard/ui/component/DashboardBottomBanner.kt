package com.example.application.dashboard.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.dashboard.data.model.PromoBanner
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.GrayDark
import com.example.application._core.ui.theme.GrayMedium
import com.example.application._core.ui.theme.WhiteSoft

@Composable
fun DashboardBottomBanner(
    banners: List<PromoBanner>
) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        banners.forEach { banner ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),

                shape = RoundedCornerShape(16.dp),

                border = BorderStroke(
                    1.dp,
                    GrayMedium
                )
            ) {

                Column {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    ) {

                        Image(
                            painter = painterResource(banner.imageRes),

                            contentDescription = null,

                            contentScale = ContentScale.Crop,

                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Column(
                        modifier = Modifier
                            .background(WhiteSoft)
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {

                        Text(
                            text = banner.title,
                            fontWeight = FontWeight.Bold,
                            color = BlackSoft,
                            fontSize = 16.sp
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = banner.description,
                            fontSize = 12.sp,
                            color = GrayDark,
                            maxLines = 2
                        )
                    }
                }
            }
        }
    }
}