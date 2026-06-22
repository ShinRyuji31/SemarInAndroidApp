package com.example.application._core.orderhistory.ui.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.GrayMedium
import com.example.application._core.util.toRupiah
import com.example.application._core.orderhistory.data.model.OrderHistory

@Composable
fun OrderHistoryItem(
    order: OrderHistory,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(1.dp, GrayMedium, RoundedCornerShape(12.dp))
            .background(Color.White, RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(width = 100.dp, height = 80.dp)
                .background(order.themeColor),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = order.imageRes),
                contentDescription = null,
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Fit
            )
        }

        Column(
            modifier = Modifier
                .padding(12.dp)
                .weight(1f)
        ) {
            Text(
                text = order.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = BlackSoft
            )
            Text(
                text = order.details,
                fontSize = 10.sp,
                color = Color.Gray,
                maxLines = 1
            )
            Text(
                text = order.dateTime,
                fontSize = 11.sp,
                color = Color.Gray
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = order.price.toRupiah(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = BlackSoft,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}
