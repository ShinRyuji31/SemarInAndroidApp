package com.example.application.driver.order.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.application._core.ui.component.ButtonBlue
import com.example.application._core.ui.component.ButtonWhite
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.WhiteSoft

@Composable
fun OrderIncomingNotification(
    price: Int = 15000,
    distanceKm: Double = 2.5,
    onAccept: () -> Unit,
    onDecline: () -> Unit
) {
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = WhiteSoft),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = "Pesanan Baru Masuk! 🛵",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = BlackSoft
                )

                Spacer(modifier = Modifier.height(16.dp))

                HorizontalDivider(color = Color.LightGray)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Lokasi Jemput: Jl. Dummy Pickup No. 1",
                    fontSize = 14.sp, color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Lokasi Antar: Gedung Dummy UNS",
                    fontSize = 14.sp, color =
                        Color.Gray
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Estimasi Jarak:",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    Text(
                        text = "$distanceKm km",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = BlackSoft
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Pendapatan:",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Rp $price",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF008938)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ButtonWhite(
                        text = "Tolak",
                        onClick = onDecline,
                        modifier = Modifier.weight(1f).height(50.dp)
                    )
                    ButtonBlue(
                        text = "Terima",
                        onClick = onAccept,
                        modifier = Modifier.weight(1f).height(50.dp)
                    )
                }
            }
        }
    }
}