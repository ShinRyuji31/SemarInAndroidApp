package com.example.application.anterin.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.application._core.ui.theme.BlackSoft
import com.example.application._core.ui.theme.GrayMedium
import com.example.application._core.ui.theme.WhiteSoft

@Composable
fun AnterinInputSection(
    title: String,
    value: String,
    placeholder: String,
    onClick: () -> Unit
) {

    Text(
        text = title,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(6.dp))

    OutlinedTextField(
        value = value,
        onValueChange = {},
        placeholder = {
            Text(placeholder, color = BlackSoft)
        },
        enabled = false,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = GrayMedium,
            disabledContainerColor = WhiteSoft,
            disabledTextColor = Color.Black
        )
    )
}