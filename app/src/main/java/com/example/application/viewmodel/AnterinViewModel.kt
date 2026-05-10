package com.example.application.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.application.data.repository.AnterinRepository
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class AnterinViewModel : ViewModel() {

    private val repository = AnterinRepository()

    val histories = MutableStateFlow(
        repository.getHistories()
    )

    val vehicles = MutableStateFlow(
        repository.getVehicles()
    )

    val route = MutableStateFlow(
        repository.getRoute()
    )

    var selectedVehicle by mutableStateOf("car")
}