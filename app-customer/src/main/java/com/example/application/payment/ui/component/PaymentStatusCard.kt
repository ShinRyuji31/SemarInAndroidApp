package com.example.application.payment.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.R
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.GrayDark
import com.example.application.payment.ui.state.PaymentState
import com.example.application._core.util.toRupiah

@Composable
fun PaymentStatusCard(
    state: PaymentState,
    orderType: String,
    totalPrice: Double
) {
    val done = state == PaymentState.COMPLETED

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Text(
            "Payment Status",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = BlackSoft)
        Text(
            text = "$orderType — ${totalPrice.toRupiah()}",
            fontSize = 16.sp,
            color = GrayDark
        )

        Spacer(Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(
                    if (done) R.drawable.ic_check
                    else R.drawable.ic_clock),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint =
                    if (done) Color(0xFF1E8E3E)
                    else Color(0xFFE6A03B)
            )

            Spacer(Modifier.width(8.dp))

            Text(
                if (done) "Payment Completed" else "Awaiting Payment",
                fontSize = 16.sp,
                color =
                    if (done) Color(0xFF1E8E3E)
                    else Color(0xFFE6A03B),
                fontWeight = FontWeight.Bold
            )
        }
    }
}