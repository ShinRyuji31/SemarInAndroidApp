package com.example.application.driver.order.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActiveOrderDto(
    @SerialName("order_id") val orderId: String,
    @SerialName("order_status") val orderStatus: String,
    @SerialName("distance") val distance: Double? = null,
    @SerialName("total_price") val totalPrice: Double? = null
)
