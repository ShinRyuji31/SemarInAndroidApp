package com.example.application.auth.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.global.ui.component.TextFieldOutlineRegular

@Composable
fun AuthInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "Enter $label",
    isPassword: Boolean = false,
    modifier: Modifier = Modifier
) {
    Text(
        text = label,
        modifier = Modifier.fillMaxWidth(),
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    )
    Spacer(modifier = Modifier.height(2.dp))
    TextFieldOutlineRegular(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        isPassword = isPassword
    )
    Spacer(modifier = Modifier.height(12.dp))
}
