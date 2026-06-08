package com.example.application._core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import com.example.application.R

val AppFont = FontFamily(
    Font(R.font.product_sans_regular, FontWeight.Normal),
    Font(R.font.product_sans_bold, FontWeight.Bold),
    Font(R.font.product_sans_italic, FontWeight.Normal, style = FontStyle.Italic),
    Font(R.font.product_sans_bold_italic, FontWeight.Bold, style = FontStyle.Italic)
    )
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = AppFont
    ),

    bodySmall = TextStyle(
        fontFamily = AppFont
    ),

    titleLarge = TextStyle(
        fontFamily = AppFont
    ),

    titleMedium = TextStyle(
        fontFamily = AppFont
    ),

    titleSmall = TextStyle(
        fontFamily = AppFont
    ),

    labelLarge = TextStyle(
        fontFamily = AppFont
    )
)