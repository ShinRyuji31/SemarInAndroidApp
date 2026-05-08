package com.example.application.data.repository

import com.example.application.R
import com.example.application.data.model.MenuFoodItem
import com.example.application.data.model.Restaurant

class JajaninRepository {

    fun getRestaurants(): List<Restaurant> {
        return listOf(

            Restaurant(
                name = "Beard Papa’s",
                address = "Jl. Slamet Riyadi No.120, Laweyan 3.5 km",
                promo = "Diskon ongkir s.d 5rb",
                rating = 4.8,
                imageRes = R.drawable.resto_sate,

                openTime = "10:00",
                closeTime = "22:00",
                openDays = "Mon-Sun",

                tags = listOf("Bakery", "Dessert")
            ),

            Restaurant(
                name = "Fore Coffee",
                address = "Jl. Kyai Haji Agus Salim 1 km",
                promo = "Gratis es kopi jumbo",
                rating = 4.7,
                imageRes = R.drawable.resto_sate,

                openTime = "07:00",
                closeTime = "23:00",
                openDays = "Daily",

                tags = listOf("Coffee", "Cafe")
            ),

            Restaurant(
                name = "Wingstop",
                address = "Jl. Brigjen Slamet Riyadi 1.4 km",
                promo = "Buy 1 Get 1",
                rating = 4.9,
                imageRes = R.drawable.resto_sate,

                openTime = "09:00",
                closeTime = "21:00",
                openDays = "Mon-Sat",

                tags = listOf("Chicken", "Fast Food")
            ),

            Restaurant(
                name = "J.CO",
                address = "Jl. Kapten Mulyadi, Surakarta 2 km",
                promo = "Diskon donut 20%",
                rating = 4.6,
                imageRes = R.drawable.resto_sate,

                openTime = "08:00",
                closeTime = "22:00",
                openDays = "Daily",

                tags = listOf("Donut", "Coffee")
            ),

            Restaurant(
                name = "Shihlin",
                address = "Jl. Slamet Riyadi 3 km",
                promo = "Combo hemat mulai 25rb",
                rating = 4.5,
                imageRes = R.drawable.resto_ayamgeprek,

                openTime = "10:00",
                closeTime = "22:00",
                openDays = "Daily",

                tags = listOf("Taiwanese", "Snack")
            ),

            Restaurant(
                name = "Tong Tji Tea House",
                address = "Jl. Veteran 1.2 km",
                promo = "Gratis topping minuman",
                rating = 4.4,
                imageRes = R.drawable.resto_kopikenangan,

                openTime = "09:00",
                closeTime = "21:00",
                openDays = "Mon-Sun",

                tags = listOf("Tea", "Cafe")
            ),

            Restaurant(
                name = "Boost",
                address = "Jl. Surakarta 1.2 km",
                promo = "Cashback 10%",
                rating = 4.7,
                imageRes = R.drawable.resto_ayamgeprek,

                openTime = "08:00",
                closeTime = "20:00",
                openDays = "Daily",

                tags = listOf("Juice", "Healthy")
            ),

            Restaurant(
                name = "Sour Sally",
                address = "Jl. Surakarta 2 km",
                promo = "Diskon yogurt family pack",
                rating = 4.5,
                imageRes = R.drawable.resto_kopikenangan,

                openTime = "10:00",
                closeTime = "22:00",
                openDays = "Daily",

                tags = listOf("Yogurt", "Dessert")
            ),

            Restaurant(
                name = "Subway",
                address = "Jl. Surakarta 1.5 km",
                promo = "Gratis minum ukuran regular",
                rating = 4.6,
                imageRes = R.drawable.resto_ayamgeprek,

                openTime = "09:00",
                closeTime = "23:00",
                openDays = "Daily",

                tags = listOf("Sandwich", "Healthy")
            ),

            Restaurant(
                name = "Sushi Tei",
                address = "Jl. Surakarta 1.8 km",
                promo = "Diskon sushi platter",
                rating = 4.9,
                imageRes = R.drawable.resto_kopikenangan,

                openTime = "11:00",
                closeTime = "22:00",
                openDays = "Mon-Sun",

                tags = listOf("Japanese", "Sushi")
            )
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