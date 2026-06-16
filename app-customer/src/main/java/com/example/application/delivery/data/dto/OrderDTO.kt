package com.example.application.delivery.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationInsertDto(
    @SerialName("location_id") val locationId: String,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("address") val address: String
)

@Serializable
data class OrderInsertDto(
    @SerialName("order_id") val orderId: String,
    @SerialName("order_date") val orderDate: String,
    @SerialName("total_price") val totalPrice: Double,
    @SerialName("order_status") val orderStatus: String,
    @SerialName("customer_id") val customerId: String,
    @SerialName("pickup_location_id") val pickupLocationId: String, // Tambahan Pickup
    @SerialName("destination_location_id") val destinationLocationId: String
)

@Serializable
data class ItemOrderInsertDto(
    @SerialName("order_id") val orderId: String,
    @SerialName("order_type") val orderType: String,
    @SerialName("store_id") val storeId: String
)

@Serializable
data class OrderItemInsertDto(
    @SerialName("order_id") val orderId: String,
    @SerialName("product_id") val productId: String
)

// TAMBAHAN BARU: Buat narik location_id dari STORE
@Serializable
data class StoreLocationDto(
    @SerialName("location_id") val locationId: String
)