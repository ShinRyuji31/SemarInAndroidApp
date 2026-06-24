package com.example.application.order.data.dto

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationInsertRequestDto(
    @SerialName("address") val address: String,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double
)

@Serializable
data class OrderInsertRequestDto(
    @SerialName("customer_id") val customerId: String,
    @SerialName("pickup_location_id") val pickupLocationId: String,
    @SerialName("destination_location_id") val destinationLocationId: String,
    @SerialName("distance") val distance: Double,
    @SerialName("total_price") val totalPrice: Double,
    @SerialName("vehicle_type") val vehicleType: String,

    @EncodeDefault
    @SerialName("is_anterin") val isAnterin: Boolean = true,
    @SerialName("order_status") val orderStatus: String = "PENDING"
)


@Serializable
data class LocationIdResponseDto(
    @SerialName("location_id") val locationId: String
)

@Serializable
data class OrderIdResponseDto(
    @SerialName("order_id") val orderId: String
)

@Serializable
data class LocationResponseDto(
    @SerialName("address") val address: String
)

@Serializable
data class StoreResponseDto(
    @SerialName("store_name") val storeName: String,
    @SerialName("store_type") val storeType: String? = null,
    @SerialName("LOCATION") val location: LocationResponseDto? = null
)

@Serializable
data class ProductResponseDto(
    @SerialName("product_name") val productName: String = "Item",
    @SerialName("product_price") val productPrice: Double = 0.0,
    @SerialName("product_img") val productImg: String? = null,
    @SerialName("STORE") val store: StoreResponseDto? = null
)

@Serializable
data class OrderItemResponseDto(
    @SerialName("PRODUCT") val product: ProductResponseDto? = null
)


@Serializable
data class ActiveOrderDto(
    @SerialName("order_id") val orderId: String,
    @SerialName("order_status") val orderStatus: String,
    @SerialName("is_anterin") val isAnterin: Boolean? = null,
    @SerialName("distance") val distance: Double? = null,
    @SerialName("total_price") val totalPrice: Double? = null,
    @SerialName("pickup") val pickupLocation: LocationResponseDto? = null,
    @SerialName("destination") val destinationLocation: LocationResponseDto? = null,
    @SerialName("ORDER_ITEM") val orderItems: List<OrderItemResponseDto>? = emptyList()
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