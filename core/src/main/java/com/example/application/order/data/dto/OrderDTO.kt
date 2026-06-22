package com.example.application.order.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("address") val address: String
)

@Serializable
data class StoreDto(
    @SerialName("store_name") val storeName: String,
    @SerialName("store_type") val storeType: String? = null,
    @SerialName("LOCATION") val location: LocationDto? = null
)

@Serializable
data class ProductDto(
    @SerialName("product_name") val productName: String = "Item",
    @SerialName("product_price") val productPrice: Double = 0.0,
    @SerialName("product_img") val productImg: String? = null,
    @SerialName("STORE") val store: StoreDto? = null
)

@Serializable
data class OrderItemDto(
    @SerialName("PRODUCT") val product: ProductDto? = null
)

@Serializable
data class ActiveOrderDto(
    @SerialName("order_id") val orderId: String,
    @SerialName("order_status") val orderStatus: String,
    @SerialName("is_anterin") val isAnterin: Boolean? = null,
    @SerialName("distance") val distance: Double? = null,
    @SerialName("total_price") val totalPrice: Double? = null,
    @SerialName("pickup") val pickupLocation: LocationDto? = null,
    @SerialName("destination") val destinationLocation: LocationDto? = null,
    @SerialName("ORDER_ITEM") val orderItems: List<OrderItemDto>? = emptyList()
) {
    val orderType: String
        get() = if (isAnterin == true) "ANTERIN" else "DELIVERY"

    val storeName: String
        get() = orderItems?.firstOrNull()?.product?.store?.storeName ?: "Unknown Store"

    val pickupAddress: String
        get() = pickupLocation?.address ?: "Unknown Pickup Address"

    val destinationAddress: String
        get() = destinationLocation?.address ?: "Unknown Destination Address"
}