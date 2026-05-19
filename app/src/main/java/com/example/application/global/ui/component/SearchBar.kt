package com.example.application.global.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.global.ui.theme.BlackSoft
import com.example.application.global.ui.theme.BluePrimary
import com.example.application.global.ui.theme.GrayMedium

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,

        textStyle = TextStyle(
            fontSize = 12.sp,
            color = Color.Black
        ),

        placeholder = {
            Text(
                placeholderText,
                fontSize = 12.sp,
                color = BlackSoft
            )
        },

        singleLine = true,

        shape = RoundedCornerShape(10.dp),

        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedBorderColor = BluePrimary,
            unfocusedBorderColor = GrayMedium
        ),

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .height(45.dp)
    )
}