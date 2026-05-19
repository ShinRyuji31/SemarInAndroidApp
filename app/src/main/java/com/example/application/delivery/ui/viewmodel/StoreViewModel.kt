package com.example.application.delivery.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.application.delivery.data.model.StoreInventory
import com.example.application.delivery.data.model.Store
import com.example.application.delivery.data.repository.StoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StoreViewModel(
    private val repository: StoreRepository = StoreRepository()
) : ViewModel() {

    private val _stores = MutableStateFlow<List<Store>>(emptyList())
    val stores: StateFlow<List<Store>> = _stores

    private val _inventory = MutableStateFlow<List<StoreInventory>>(emptyList())
    val inventory: StateFlow<List<StoreInventory>> = _inventory

    private val _selectedStore = MutableStateFlow<Store?>(null)
    val selectedStore: StateFlow<Store?> = _selectedStore

    init {
        loadData()
    }

    private fun loadData() {
        _stores.value = repository.getStore()
        _inventory.value = repository.getStoreInventory()
    }

    fun selectStore(store: Store) {
        _selectedStore.value = store
    }

}