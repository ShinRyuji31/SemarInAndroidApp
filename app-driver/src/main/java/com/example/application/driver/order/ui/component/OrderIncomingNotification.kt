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
import com.example.application._core.ui.theme.BluePrimary
import com.example.application._core.ui.theme.WhiteSoft
import com.example.application._core.util.toRupiah
import com.example.application.order.data.dto.ActiveOrderDto

@Composable
fun OrderIncomingNotification(
    order: ActiveOrderDto,
    onAccept: () -> Unit,
    onDecline: () -> Unit
) {
    val isAnterin = order.isAnterin == true

    val serviceLabel = if (isAnterin) "Layanan: Anter-In Ride" else "Layanan: Jajan-In Delivery"
    val pickupLabel = if (isAnterin) "Lokasi Jemput:" else "Lokasi Resto/Toko:"
    val destinationLabel = if (isAnterin) "Lokasi Tujuan:" else "Tujuan Pengiriman:"

    val pickupText = if (isAnterin) order.pickupAddress else (order.storeName.takeIf { it != "Unknown Store" } ?: order.pickupAddress)
    val destinationText = order.destinationAddress
    val distanceText = String.format("%.1f km", order.distance ?: 0.0)
    val priceText = (order.totalPrice?.toInt() ?: 0).toRupiah()

    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = WhiteSoft),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Pesanan Baru Masuk!",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = BlackSoft
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = serviceLabel,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = BluePrimary
                )

                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.6f))
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = pickupLabel,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = pickupText,
                    fontSize = 14.sp,
                    color = BlackSoft
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = destinationLabel,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = destinationText,
                    fontSize = 14.sp,
                    color = BlackSoft
                )

                Spacer(modifier = Modifier.height(14.dp))
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.4f))
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Estimasi Jarak:",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = distanceText,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = BlackSoft
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Pendapatan:",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = priceText,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = BluePrimary
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ButtonWhite(
                        text = "Tolak",
                        onClick = onDecline,
                        modifier = Modifier.weight(1f).height(48.dp)
                    )
                    ButtonBlue(
                        text = "Terima",
                        onClick = onAccept,
                        modifier = Modifier.weight(1f).height(48.dp)
                    )
                }
            }
        }
    }
}