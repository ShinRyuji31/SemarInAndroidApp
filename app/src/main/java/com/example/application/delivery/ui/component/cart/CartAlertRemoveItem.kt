package com.example.application.delivery.ui.component.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application._core.ui.component.ButtonBlue
import com.example.application._core.ui.component.ButtonWhite
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.WhiteSoft

@Composable
fun CartAlertRemoveItem(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.4f))
            .clickable { onDismiss() },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(24.dp)
                .background(
                    color = WhiteSoft,
                    shape = RoundedCornerShape(20.dp)
                )
                .clickable(enabled = false) {}
                .padding(20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Text(
                        text = "✕",
                        fontSize = 16.sp,
                        color = BlackSoft,
                        modifier = Modifier.clickable { onDismiss() }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Hapus Item",
                    fontSize = 18.sp,
                    color = BlackSoft,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Apakah kamu yakin ingin menghapus item ini dari keranjang?",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ButtonWhite(
                        text = "Batal",
                        onClick = onDismiss,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                    )

                    ButtonBlue(
                        text = "Hapus",
                        onClick = onConfirm,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                    )
                }
            }
        }
    }
}