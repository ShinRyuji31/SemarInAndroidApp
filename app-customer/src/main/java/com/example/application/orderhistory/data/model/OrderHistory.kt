package com.example.application.orderhistory.data.model

import androidx.compose.ui.graphics.Color

enum class OrderType {
    FOOD,
    ANTERIN
}

data class OrderHistory(
    val id: String,
    val name: String,
    val details: String,
    val dateTime: String,
    val price: Int,
    val imageRes: Int,
    val type: OrderType,
    val themeColor: Color
)
