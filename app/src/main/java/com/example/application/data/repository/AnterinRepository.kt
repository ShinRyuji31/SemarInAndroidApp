package com.example.application.data.repository

import com.example.application.R
import com.example.application.data.model.anterin.HistoryLocation
import com.example.application.data.model.anterin.VehicleType
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

    fun getVehicleTypes(): List<VehicleType> {
        return listOf(
            VehicleType(
                id = "car",
                name = "Car",
                capacity = 4,
                price = 40320,
                icon = R.drawable.ic_bike
            ),

            VehicleType(
                id = "bike",
                name = "Bike",
                capacity = 1,
                price = 20160,
                icon = R.drawable.ic_bike
            )
        )
    }

}