package com.example.application.ui.component.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.application.data.model.Restaurant
import com.example.application.ui.theme.BlackSoft
import com.example.application.ui.theme.GrayMedium

@Composable
fun DashboardAffordableRestaurant(
    restaurants: List<Restaurant>
) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text(
            text = "Semar Resto Termurah",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = BlackSoft
        )

        LazyRow {

            items(restaurants) { resto ->

                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(160.dp)
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

                    Column {

                        Image(
                            painter = painterResource(id = resto.imageRes),

                            contentDescription = null,

                            contentScale = ContentScale.Crop,

                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxWidth()
                                .clip(
                                    RoundedCornerShape(
                                        topStart = 16.dp,
                                        topEnd = 16.dp
                                    )
                                )
                        )

                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {

                            Text(
                                resto.name,
                                fontWeight = FontWeight.Bold,
                                color = BlackSoft
                            )

                            Text(
                                text = "⭐ ${resto.rating}",
                                fontSize = 12.sp,
                                color = BlackSoft
                            )
                        }
                    }
                }
            }
        }
    }
}