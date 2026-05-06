package com.example.application.ui.component.shared.delivery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.R
import com.example.application.ui.theme.BluePrimary
import com.example.application.ui.theme.GrayDark
import com.example.application.ui.theme.WhiteSoft

@Composable
fun DeliveryItemCard(
    name: String,
    address: String,
    rating: Double,
    imageRes: Int,
    promo: String,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(WhiteSoft)
            .border(
                width = 1.dp,
                color = GrayDark,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
    ) {

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
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
                .padding(12.dp)
                .weight(1f)
        ) {

            Text(name, fontWeight = FontWeight.Bold, fontSize = 16.sp)

            Spacer(modifier = Modifier.height(4.dp))
            Text(address, fontSize = 12.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(2.dp))
            Text(promo, fontSize = 12.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = rating.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}