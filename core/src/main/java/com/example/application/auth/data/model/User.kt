package com.example.application.auth.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("user_id")
    val id: String,
    val username: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String? = null,
    @SerialName("sso_email")
    val email: String,
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("profile_pic")
    val profilePic: String? = null,
    @SerialName("time_created")
    val timeCreated: String? = null,
    @SerialName("time_updated")
    val timeUpdated: String? = null
)

@Serializable
data class Customer(
    @SerialName("customer_id")
    val customerId: String,
    @SerialName("customer_tier")
    val customerTier: String = "Regular"
)
