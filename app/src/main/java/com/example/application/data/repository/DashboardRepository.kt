package com.example.application.data.repository

import com.example.application.R
import com.example.application.data.model.PromoBanner
import com.example.application.data.model.Restaurant
import com.example.application.data.model.User

class DashboardRepository {

    fun getTopBanners(): List<PromoBanner> {
        return listOf(
            PromoBanner(
                title = "Promo Spesial",
                description = "Diskon hingga 70%",
                imageRes = R.drawable.banner_promosatu
            ),

            PromoBanner(
                title = "Gratis Ongkir",
                description = "Khusus pengguna baru",
                imageRes = R.drawable.banner_promodua
            ),

            PromoBanner(
                title = "Cashback Besar",
                description = "Bayar pakai e-wallet",
                imageRes = R.drawable.banner_promotiga
            ),

            PromoBanner(
                title = "Promo Tengah Malam",
                description = "Diskon makanan favorit",
                imageRes = R.drawable.banner_promoempat
            ),

            PromoBanner(
                title = "Weekend Deal",
                description = "Promo akhir pekan",
                imageRes = R.drawable.banner_promolima
            )
        )
    }

    fun getBottomBanners(): List<PromoBanner> {
        return listOf(

            PromoBanner(
                title = "Anter-In Lagi Disini",
                description = "Saya akan kembali ke Solo sebagai rakyat biasa...",
                imageRes = R.drawable.banner_anterin
            ),

            PromoBanner(
                title = "Titip-In Promo Baru",
                description = "Titip barang lebih mudah dan cepat.",
                imageRes = R.drawable.banner_titipin
            )
        )
    }

    fun getAffordableRestaurants(): List<Restaurant> {

        return JajaninRepository()
            .getRestaurants()
            .sortedByDescending { it.rating }
            .take(3)
    }

    fun getLastOrderRestaurant(): Restaurant {

        return JajaninRepository()
            .getRestaurants()
            .first()
    }
}