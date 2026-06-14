package com.example.application.delivery.ui.component.store

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.R
import com.example.application._core.ui.theme.GrayMedium
import com.example.application._core.ui.theme.Yellow
import com.example.application.delivery.data.model.Store

@Composable
fun StoreInfoCard(
    store: Store
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .border(
                    width = 1.dp,
                    color = GrayMedium,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = store.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = store.address,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Opening Hours",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${store.openTime} - ${store.closeTime} WIB (${store.openDays})",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_star),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Yellow
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = store.rating.toString(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    store.tags.forEach { tag ->
                        StoreInfoCardTag(text = tag)
                    }
                }
            }
        }
    }
}
