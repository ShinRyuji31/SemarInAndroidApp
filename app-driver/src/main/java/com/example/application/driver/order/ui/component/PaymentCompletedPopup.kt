package com.example.application.driver.order.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.application._core.ui.component.ButtonBlue
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.WhiteSoft

@Composable
fun PaymentCompletedPopup(
    totalPrice: Double?,
    onConfirm: () -> Unit
) {
    Dialog(
        onDismissRequest = { /* Cannot dismiss */ },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = WhiteSoft, shape = RoundedCornerShape(16.dp))
                .padding(24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Menunggu Pembayaran",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = BlackSoft
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Total yang harus dibayar:",
                    fontSize = 14.sp,
                    color = BlackSoft
                )
                
                Spacer(modifier = Modifier.height(8.dp))

                val priceDisplay = if (totalPrice != null) "Rp${totalPrice.toLong()}" else "Rp-"
                Text(
                    text = priceDisplay,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 28.sp,
                    color = BlackSoft
                )

                Spacer(modifier = Modifier.height(24.dp))

                ButtonBlue(
                    text = "Payment Completed",
                    onClick = onConfirm,
                    modifier = Modifier.fillMaxWidth().height(52.dp)
                )
            }
        }
    }
}
