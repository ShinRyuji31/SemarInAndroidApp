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
import com.example.application._core.ui.component.TextFieldOutlineRegular
import com.example.application._core.ui.theme.BlackSoft

@Composable
fun AuthInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "Enter $label",
    isPassword: Boolean = false,
    enabled: Boolean = true
) {
    Text(
        text = label,
        modifier = Modifier.fillMaxWidth(),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = BlackSoft
    )
    Spacer(modifier = Modifier.height(2.dp))
    TextFieldOutlineRegular(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        isPassword = isPassword,
        enabled = enabled
    )
    Spacer(modifier = Modifier.height(4.dp))
}
