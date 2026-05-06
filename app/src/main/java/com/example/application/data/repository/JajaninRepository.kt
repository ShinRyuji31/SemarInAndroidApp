package com.example.application.data.repository

import com.example.application.R
import com.example.application.data.model.MenuFoodItem
import com.example.application.data.model.Restaurant

class JajaninRepository {

    fun getRestaurants(): List<Restaurant> {
        return listOf(
            Restaurant("Beard Papa’s", "Jl. Slamet Riyadi No.120, Laweyan 3.5 km", "Diskon ongkir s.d 5rb", 4.8, R.drawable.resto_sate),
            Restaurant("Fore Coffee", "Jl. Kyai Haji Agus Salim 1 km", "Diskon ongkir s.d 5rb", 4.8, R.drawable.resto_sate),
            Restaurant("Wingstop", "Jl. Brigjen Slamet Riyadi 1.4 km", "Diskon ongkir s.d 5rb", 4.8, R.drawable.resto_sate),
            Restaurant("J.CO", "Jl. Kapten Mulyadi, Surakarta 2 km", "Diskon ongkir s.d 5rb", 4.8, R.drawable.resto_sate),
            Restaurant("Shihlin", "Jl. Slamet Riyadi 3 km", "Diskon ongkir s.d 5rb", 4.8, R.drawable.resto_ayamgeprek),
            Restaurant("Tong Tji Tea House", "Jl. Veteran 1.2 km", "Diskon ongkir s.d 5rb", 4.8, R.drawable.resto_kopikenangan),
            Restaurant("Boost", "Jl. Surakarta 1.2 km", "Diskon ongkir s.d 5rb", 4.8, R.drawable.resto_ayamgeprek),
            Restaurant("Sour Sally", "Jl. Surakarta 2 km", "Diskon ongkir s.d 5rb", 4.8, R.drawable.resto_kopikenangan),
            Restaurant("Subway", "Jl. Surakarta 1.5 km", "Diskon ongkir s.d 5rb", 4.8, R.drawable.resto_ayamgeprek),
            Restaurant("Sushi Tei", "Jl. Surakarta 1.8 km", "Diskon ongkir s.d 5rb", 4.8, R.drawable.resto_kopikenangan)
        )
    }

    fun getMenu(): List<MenuFoodItem> {
        return listOf(
            MenuFoodItem("Delight Box", "Rp29.000", R.drawable.change_this_to_correct_pic),
            MenuFoodItem("Family Bundle", "Rp45.000", R.drawable.change_this_to_correct_pic),
            MenuFoodItem("Eclair Chocolate", "Rp9.000", R.drawable.change_this_to_correct_pic),
            MenuFoodItem("Vanilla Custard", "Rp8.000", R.drawable.change_this_to_correct_pic)
        )
    }
}