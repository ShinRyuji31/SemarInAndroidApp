package com.example.application.dashboard.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.example.application.global.ui.component.RatingStar
import com.example.application.global.ui.theme.BlackSoft
import com.example.application.global.ui.theme.GrayMedium

@Composable
fun DashboardAffordableRestaurant(
    stores: List<Store>
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

            items(stores) { resto ->

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

                        // Menggunakan AsyncImage dari Coil untuk handle imageUrl dan fallback imageRes
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(resto.imageUrl ?: resto.imageRes ?: R.drawable.dummy)
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(id = R.drawable.dummy),
                            error = painterResource(id = R.drawable.dummy),
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

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RatingStar()

                                Spacer(modifier = Modifier.width(4.dp))

                                Text(
                                    text = "${resto.rating}",
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
}