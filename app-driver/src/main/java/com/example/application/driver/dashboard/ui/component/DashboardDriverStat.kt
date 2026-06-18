package com.example.application.driver.dashboard.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application._core.ui.theme.BluePrimary

@Composable
fun DashboardDriverStat() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Card(
            modifier = Modifier.weight(1f),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Target Harian",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    "0/10",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = BluePrimary
                )
            }
        }

        Card(
            modifier = Modifier.weight(1f),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Pendapatan",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    "Rp 0",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = BluePrimary
                )
            }
        }
    }
}