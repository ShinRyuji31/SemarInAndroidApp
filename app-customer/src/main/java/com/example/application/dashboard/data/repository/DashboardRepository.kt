package com.example.application.dashboard.data.repository

import com.example.application.R
import com.example.application.dashboard.data.model.PromoBanner
import com.example.application.delivery.data.model.Store
import com.example.application.delivery.data.repository.SupabaseStoreRepository

class DashboardRepository(private val supabaseStoreRepository: SupabaseStoreRepository) {

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

    suspend fun getAffordableRestaurants(): List<Store> {
        return supabaseStoreRepository.fetchStores().fold(
            onSuccess = { stores ->
                stores.sortedByDescending { it.rating }.take(3)
            },
            onFailure = {
                emptyList()
            }
        )
    }

    suspend fun getLastOrderRestaurant(): Store? {
        return supabaseStoreRepository.fetchStores().fold(
            onSuccess = { stores ->
                stores.firstOrNull()
            },
            onFailure = {
                null
            }
        )
    }
}