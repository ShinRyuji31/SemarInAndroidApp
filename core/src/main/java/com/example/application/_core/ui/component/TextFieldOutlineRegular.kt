package com.example.application._core.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.application._core.ui.theme.BlackSoft

@Composable
fun TextFieldOutlineRegular(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false,
    enabled: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,

        placeholder = {
            Text(placeholder)
        },

        visualTransformation =
            if (isPassword)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,

        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),

        shape = RoundedCornerShape(12.dp),

        colors = OutlinedTextFieldDefaults.colors(

            focusedTextColor = BlackSoft,
            unfocusedTextColor = BlackSoft,
            disabledTextColor = BlackSoft,

            focusedPlaceholderColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Gray,

            focusedBorderColor = Color(0xFFDADADA),
            unfocusedBorderColor = Color(0xFFDADADA),
            disabledBorderColor = Color(0xFFDADADA),

            cursorColor = BlackSoft
        )
    )
}