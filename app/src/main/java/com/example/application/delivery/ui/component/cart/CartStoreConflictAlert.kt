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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application._core.ui.component.ButtonBlue
import com.example.application._core.ui.component.ButtonWhite
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.WhiteSoft

@Composable
fun CartStoreConflictAlert(
    currentStoreType: String,
    newStoreType: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val title = when {
        currentStoreType == "FOOD" && newStoreType == "FOOD" -> "Mau makan ini aja nih?"
        currentStoreType == "FOOD" && newStoreType == "RETAIL" -> "Ngga jadi makan nih?"
        currentStoreType == "RETAIL" && newStoreType == "RETAIL" -> "Jadinya beli dari toko ini aja?"
        currentStoreType == "RETAIL" && newStoreType == "FOOD" -> "Laper nih?"
        else -> "Ganti Toko?"
    }

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
                    text = title,
                    fontSize = 18.sp,
                    color = BlackSoft,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Item dari toko sebelumnya akan dihapus dari keranjang ya. Lanjut?",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
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
                        text = "Ganti",
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