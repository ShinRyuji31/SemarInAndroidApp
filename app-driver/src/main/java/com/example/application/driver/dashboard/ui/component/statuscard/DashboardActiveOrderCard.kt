package com.example.application.driver.dashboard.ui.component.statuscard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application._core.ui.component.ButtonWhite
import com.example.application._core.ui.theme.WhiteSoft
import com.example.application._core.ui.theme.blueBlueGradient

@Composable
fun DashboardActiveOrderCard(
    onDetailClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(blueBlueGradient())
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Deliver Food",
                color = WhiteSoft,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Gg. Kutai Utara No.1",
                color = WhiteSoft,
                fontSize = 14.sp
            )
            Text(
                text = "Jarak 3.5 km",
                color = WhiteSoft,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        ButtonWhite(
            text = "Detail",
            onClick = onDetailClick,
            modifier = Modifier.width(100.dp).height(50.dp)
        )
    }
}