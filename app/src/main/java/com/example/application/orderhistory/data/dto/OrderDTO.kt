package com.example.application.orderhistory.data.dto

import com.example.application.delivery.data.model.dto.StoreDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderDto(
    @SerialName("order_id") val orderId: String,
    @SerialName("order_date") val orderDate: String,
    @SerialName("total_price") val totalPrice: Double,
    @SerialName("order_status") val orderStatus: String? = null,
    @SerialName("vehicle_type") val vehicleType: String? = null,
    @SerialName("distance") val distance: Double? = null,
    @SerialName("ITEM_ORDER") val itemOrders: List<ItemOrderDto>? = emptyList()
)

@Serializable
data class ItemOrderDto(
    @SerialName("order_type") val orderType: String,
    @SerialName("STORE") val store: StoreDto? = null
)