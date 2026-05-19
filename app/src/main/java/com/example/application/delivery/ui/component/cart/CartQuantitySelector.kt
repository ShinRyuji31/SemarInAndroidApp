package com.example.application.delivery.ui.component.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.global.ui.component.CircleButton

@Composable
fun CartQuantitySelector(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        CircleButton(
            text = "-",
            modifier = Modifier.size(20.dp),
            onClick = {
                if (quantity > 1) {
                    onDecrease()
                }
            }
        )

        Text(
            text = quantity.toString(),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        CircleButton(
            text = "+",
            modifier = Modifier.size(20.dp),
            onClick = {
                onIncrease()
            }
        )
    }
}