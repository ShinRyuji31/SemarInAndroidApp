package com.example.application.delivery.data.repository

import com.example.application.delivery.data.model.Store
import com.example.application.delivery.data.model.StoreInventory
import com.example.application.delivery.data.model.StoreType
import com.example.application.store.data.dto.ProductDto
import com.example.application.store.data.dto.StoreDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale

class SupabaseStoreRepository(
    private val supabase: SupabaseClient
) {

    suspend fun fetchStores(): Result<List<Store>> = withContext(Dispatchers.IO) {
        try {
            val dtos = supabase.postgrest["STORE"]
                .select(columns = Columns.raw("*, LOCATION(*), tags:STORE_TAG(*), PRODUCT(*)"))
                .decodeList<StoreDto>()

            val stores = dtos.map { dto ->
                val mappedType = when (dto.storeType?.uppercase(Locale.ROOT)) {
                    "FOOD" -> StoreType.FOOD
                    "RETAIL" -> StoreType.RETAIL
                    else -> StoreType.FOOD
                }

                Store(
                    id = dto.storeId,
                    name = dto.storeName,
                    address = dto.location?.address ?: "Alamat tidak tersedia",
                    promo = "Cek promo di aplikasi",
                    rating = dto.rating ?: 0.0,
                    imageUrl = dto.logoImg,
                    openTime = dto.openHour?.take(5) ?: "00:00",
                    closeTime = dto.closeHour?.take(5) ?: "00:00",
                    openDays = "Setiap Hari",
                    tags = dto.tags?.map { it.tagName } ?: emptyList(),
                    type = mappedType,
                    productNames = dto.products?.map { it.productName } ?: emptyList()
                )
            }
            Result.success(stores)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun fetchProductsByStoreId(storeId: String): Result<List<StoreInventory>> = withContext(Dispatchers.IO) {
        try {
            val dtos = supabase.postgrest["PRODUCT"]
                .select(columns = Columns.raw("*, PRODUCT_CATEGORY(*)")) {
                    filter {
                        eq("store_id", storeId)
                    }
                }
                .decodeList<ProductDto>()

            val inventories = dtos.map { dto ->
                StoreInventory(
                    id = dto.productId,
                    storeId = dto.storeId,
                    name = dto.productName,
                    price = dto.productPrice.toInt(),
                    imageUrl = dto.productImg,
                    category = dto.category?.categoryName ?: "Umum"
                )
            }
            Result.success(inventories)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}