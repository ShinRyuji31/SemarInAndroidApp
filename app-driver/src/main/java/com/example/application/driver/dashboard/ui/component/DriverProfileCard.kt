package com.example.application.driver.dashboard.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application._core.ui.theme.BluePrimary

@Composable
fun DriverProfileCard(
    isOnline: Boolean,
    onToggleOnline: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(60.dp).clip(CircleShape).background(Color.LightGray))
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text("Driver Aktif", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text("Mahasiswa UNS", color = Color.Gray, fontSize = 14.sp)
                }
            }

            // Saklar Online / Offline
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Switch(
                    checked = isOnline,
                    onCheckedChange = onToggleOnline,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = BluePrimary
                    )
                )
                Text(
                    text = if (isOnline) "Online" else "Offline",
                    color = if (isOnline) BluePrimary else Color.Gray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}