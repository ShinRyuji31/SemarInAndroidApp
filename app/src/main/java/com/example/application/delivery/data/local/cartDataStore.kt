package com.example.application.delivery.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.application.delivery.data.model.CartItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private val Context.dataStore by preferencesDataStore(name = "cart_store")

class CartDataStore(
    private val context: Context

) {

    companion object {
        private val CART_KEY = stringPreferencesKey("cart_items")
    }

    private val jsonParser = Json {
        ignoreUnknownKeys = true
    }
    fun getCartItems(): Flow<List<CartItem>> {
        return context.dataStore.data.map { pref ->

            val json = pref[CART_KEY] ?: "[]"

            try {
                jsonParser.decodeFromString<List<CartItem>>(json)
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    suspend fun saveCartItems(
        items: List<CartItem>
    ) {

        context.dataStore.edit { pref ->

            pref[CART_KEY] = Json.encodeToString(items)
        }
    }
}