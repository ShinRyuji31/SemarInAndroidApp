package com.example.application.data.repository

import com.example.application.R
import com.example.application.data.model.anterin.HistoryLocation
import com.example.application.data.model.anterin.Vehicle
import com.example.application.data.model.anterin.DrivingRoute

class AnterinRepository {

    fun getHistories(): List<HistoryLocation> {
        return listOf(
            HistoryLocation(
                id = 1,
                address = "Gg. Kutai Utara No. 1, Sumber"
            ),

            HistoryLocation(
                id = 2,
                address = "Faculty of Law UNS, Jl. Ir. Sutami No.36A"
            ),

            HistoryLocation(
                id = 3,
                address = "Lokananta Bloc, Kerten"
            )
        )
    }

    fun getVehicles(): List<Vehicle> {
        return listOf(
            Vehicle(
                id = "car",
                name = "Car",
                capacity = 4,
                price = 40320,
                icon = R.drawable.ic_bike
            ),

            Vehicle(
                id = "bike",
                name = "Bike",
                capacity = 1,
                price = 20160,
                icon = R.drawable.ic_bike
            )
        )
    }

    fun getRoute(): DrivingRoute {
        return DrivingRoute(
            pickup = "Lokananta Bloc, Kerten",
            destination = "Gedung B FMIPA UNS",
            distance = "5.2 km"
        )
    }
}