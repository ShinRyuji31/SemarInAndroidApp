package com.example.application.viewmodel

import androidx.lifecycle.ViewModel
import com.example.application.data.model.MenuFoodItem
import com.example.application.data.model.Restaurant
import com.example.application.data.repository.JajaninRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class JajaninViewModel(
    private val repository: JajaninRepository = JajaninRepository()
) : ViewModel() {

    private val _restaurants = MutableStateFlow<List<Restaurant>>(emptyList())
    val restaurants: StateFlow<List<Restaurant>> = _restaurants

    private val _menu = MutableStateFlow<List<MenuFoodItem>>(emptyList())
    val menu: StateFlow<List<MenuFoodItem>> = _menu

    init {
        loadData()
    }

    private fun loadData() {
        _restaurants.value = repository.getRestaurants()
        _menu.value = repository.getMenu()
    }
}