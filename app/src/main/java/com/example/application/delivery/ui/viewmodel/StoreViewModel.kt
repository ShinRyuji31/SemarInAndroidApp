package com.example.application.delivery.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.delivery.data.model.StoreInventory
import com.example.application.delivery.data.model.Store
import com.example.application.delivery.data.repository.SupabaseStoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StoreViewModel(
    private val repository: SupabaseStoreRepository
) : ViewModel() {

    private val _stores = MutableStateFlow<List<Store>>(emptyList())
    val stores: StateFlow<List<Store>> = _stores
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val filteredStores: StateFlow<List<Store>> = combine(_stores, _searchQuery) { storeList, query ->
        if (query.isBlank()) {
            storeList
        } else {
            storeList.filter { store ->
                store.name.contains(query, ignoreCase = true) ||
                store.tags.any { tag -> tag.contains(query, ignoreCase = true) } ||
                store.productNames.any { product -> product.contains(query, ignoreCase = true) }
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _inventory = MutableStateFlow<List<StoreInventory>>(emptyList())
    val inventory: StateFlow<List<StoreInventory>> = _inventory

    private val _selectedStore = MutableStateFlow<Store?>(null)
    val selectedStore: StateFlow<Store?> = _selectedStore

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                repository.fetchStores()
                    .onSuccess { _stores.value = it }
                    .onFailure { Log.e("StoreViewModel", "fetchStores error", it) }

                // Optionally, fetch inventory for first store or leave empty until selection
                _inventory.value = emptyList()
            } catch (e: Exception) {
                Log.e("StoreViewModel", "loadData error", e)
            }
        }
    }

    fun selectStore(store: Store) {
        _selectedStore.value = store

        viewModelScope.launch {
            try {
                repository.fetchProductsByStoreId(store.id)
                    .onSuccess { _inventory.value = it }
                    .onFailure { Log.e("StoreViewModel", "fetchProductsByStoreId error", it) }
            } catch (e: Exception) {
                Log.e("StoreViewModel", "selectStore error", e)
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

}
