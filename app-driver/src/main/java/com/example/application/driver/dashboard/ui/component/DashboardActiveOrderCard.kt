package com.example.application.driver.dashboard.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application._core.ui.component.ButtonWhite
import com.example.application._core.ui.theme.BluePrimary

@Composable
fun DashboardActiveOrderCard(
    onDetailClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(BluePrimary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Deliver Food",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Gg. Kutai Utara No.1",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 13.sp
            )
            Text(
                text = "Jarak 3.5 km",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        ButtonWhite(
            text = "Detail",
            onClick = onDetailClick,
            modifier = Modifier.width(100.dp).height(40.dp)
        )
    }
}