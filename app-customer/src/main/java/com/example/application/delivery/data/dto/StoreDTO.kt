package com.example.application.delivery.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreDto(
    @SerialName("store_id") val storeId: String,
    @SerialName("store_name") val storeName: String,
    @SerialName("rating") val rating: Double? = 0.0,
    @SerialName("logo_img") val logoImg: String? = null,
    @SerialName("banner_img") val bannerImg: String? = null,
    @SerialName("location_id") val locationId: String? = null,
    @SerialName("store_type") val storeType: String? = "FOOD",
    @SerialName("open_hour") val openHour: String? = "00:00:00",
    @SerialName("close_hour") val closeHour: String? = "00:00:00",
    @SerialName("LOCATION") val location: LocationDto? = null,
    @SerialName("tags") val tags: List<StoreTagDto>? = emptyList(),
    @SerialName("PRODUCT") val products: List<ProductDto>? = emptyList()
)

@Serializable
data class LocationDto(
    @SerialName("location_id") val locationId: String,
    @SerialName("address") val address: String? = "",
    @SerialName("latitude") val latitude: Double? = 0.0,
    @SerialName("longitude") val longitude: Double? = 0.0
)

@Serializable
data class StoreTagDto(
    @SerialName("store_id") val storeId: String,
    @SerialName("tag_name") val tagName: String
)

@Serializable
data class ProductDto(
    @SerialName("product_id") val productId: String,
    @SerialName("product_name") val productName: String,
    @SerialName("product_price") val productPrice: Double,
    @SerialName("product_img") val productImg: String? = null,
    @SerialName("stock") val stock: Long,
    @SerialName("store_id") val storeId: String,
    @SerialName("product_category_id") val categoryId: String? = null,
    @SerialName("PRODUCT_CATEGORY") val category: ProductCategoryDto? = null
)

@Serializable
data class ProductCategoryDto(
    @SerialName("product_category_id") val categoryId: String,
    @SerialName("category_name") val categoryName: String
)