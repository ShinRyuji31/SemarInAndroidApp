package com.example.application.driver.order.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("address") val address: String
)

@Serializable
data class StoreDto(
    @SerialName("store_name") val storeName: String
)

@Serializable
data class ItemOrderDto(
    @SerialName("STORE") val store: StoreDto? = null
)

@Serializable
data class ActiveOrderDto(
    @SerialName("order_id") val orderId: String,
    @SerialName("order_status") val orderStatus: String,
    @SerialName("distance") val distance: Double? = null,
    @SerialName("total_price") val totalPrice: Double? = null,
    @SerialName("pickup") val pickupLocation: LocationDto? = null,
    @SerialName("destination") val destinationLocation: LocationDto? = null,
    @SerialName("ITEM_ORDER") val itemOrders: List<ItemOrderDto>? = emptyList()
) {
    val storeName: String
        get() = itemOrders?.firstOrNull()?.store?.storeName ?: "Unknown Store"

    val pickupAddress: String
        get() = pickupLocation?.address ?: "Unknown Pickup Address"

    val destinationAddress: String
        get() = destinationLocation?.address ?: "Unknown Destination Address"
}
