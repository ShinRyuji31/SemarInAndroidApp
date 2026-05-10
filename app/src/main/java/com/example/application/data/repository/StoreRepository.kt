package com.example.application.data.repository

import com.example.application.R
import com.example.application.data.model.Store
import com.example.application.data.model.StoreInventory

class StoreRepository {

    fun getStore(): List<Store> {
        return listOf(

            // ================= FOOD STORE =================

            Store(
                id = "F1",
                name = "Beard Papa’s",
                address = "Jl. Slamet Riyadi No.120, Laweyan 3.5 km",
                promo = "Diskon ongkir s.d 5rb",
                rating = 4.8,
                imageRes = R.drawable.dummy,
                openTime = "10:00",
                closeTime = "22:00",
                openDays = "Mon-Sun",
                tags = listOf("Bakery", "Dessert")
            ),

            Store(
                id = "F2",
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

            Store(
                id = "F3",
                name = "Wingstop",
                address = "Jl. Brigjen Slamet Riyadi 1.4 km",
                promo = "Buy 1 Get 1",
                rating = 4.9,
                imageRes = R.drawable.resto_ayamgeprek,
                openTime = "09:00",
                closeTime = "21:00",
                openDays = "Mon-Sat",
                tags = listOf("Chicken", "Fast Food")
            ),

            Store(
                id = "F4",
                name = "J.CO Donuts & Coffee",
                address = "Jl. Kapten Mulyadi, Surakarta 2 km",
                promo = "Promo J.Cronut",
                rating = 4.6,
                imageRes = R.drawable.resto_kopikenangan,
                openTime = "08:00",
                closeTime = "22:00",
                openDays = "Daily",
                tags = listOf("Donuts", "Coffee")
            ),

            Store(
                id = "F5",
                name = "Chatime",
                address = "Solo Grand Mall, Penumping",
                promo = "Buy 2 Get 1 Free",
                rating = 4.4,
                imageRes = R.drawable.dummy,
                openTime = "10:00",
                closeTime = "22:00",
                openDays = "Daily",
                tags = listOf("Milk Tea", "Beverage")
            ),

            // ================= RETAIL STORE =================

            Store(
                id = "R1",
                name = "Gramedia",
                address = "Solo Paragon Mall",
                promo = "Diskon alat tulis 20%",
                rating = 4.8,
                imageRes = R.drawable.dummy,
                openTime = "09:00",
                closeTime = "22:00",
                openDays = "Daily",
                tags = listOf("Bookstore", "Stationery")
            ),

            Store(
                id = "R2",
                name = "Erafone",
                address = "Solo Square Mall",
                promo = "Cicilan 0%",
                rating = 4.7,
                imageRes = R.drawable.dummy,
                openTime = "10:00",
                closeTime = "21:00",
                openDays = "Daily",
                tags = listOf("Electronics", "Gadget")
            ),

            Store(
                id = "R3",
                name = "Planet Sports",
                address = "Solo Grand Mall",
                promo = "Sepatu mulai 199rb",
                rating = 4.6,
                imageRes = R.drawable.dummy,
                openTime = "10:00",
                closeTime = "22:00",
                openDays = "Daily",
                tags = listOf("Sports", "Fashion")
            ),

            Store(
                id = "R4",
                name = "MR.DIY",
                address = "The Park Solo Baru",
                promo = "Promo serba hemat",
                rating = 4.9,
                imageRes = R.drawable.dummy,
                openTime = "09:00",
                closeTime = "22:00",
                openDays = "Daily",
                tags = listOf("Household", "Tools")
            ),

            Store(
                id = "R5",
                name = "Uniqlo",
                address = "Solo Paragon Mall",
                promo = "AIRism Special Price",
                rating = 4.8,
                imageRes = R.drawable.dummy,
                openTime = "10:00",
                closeTime = "22:00",
                openDays = "Daily",
                tags = listOf("Fashion", "Clothing")
            )
        )
    }

    fun getStoreInventory(): List<StoreInventory> {
        return listOf(

            // ================= F1 =================

            StoreInventory(
                id = "F1001",
                storeId = "F1",
                name = "Delight Box",
                price = 297000,
                imageRes = R.drawable.dummy,
                category = "Cream Puff Party Collections"
            ),

            StoreInventory(
                id = "F1002",
                storeId = "F1",
                name = "Family Bundle",
                price = 297000,
                imageRes = R.drawable.dummy,
                category = "Cream Puff Party Collections"
            ),

            StoreInventory(
                id = "F1003",
                storeId = "F1",
                name = "Party Box",
                price = 120000,
                imageRes = R.drawable.dummy,
                category = "Cream Puff Party Collections"
            ),

            StoreInventory(
                id = "F1004",
                storeId = "F1",
                name = "Mini Box",
                price = 15000,
                imageRes = R.drawable.dummy,
                category = "Cream Puff Party Collections"
            ),

            StoreInventory(
                id = "F1005",
                storeId = "F1",
                name = "Super Bundle",
                price = 200000,
                imageRes = R.drawable.dummy,
                category = "Cream Puff Party Collections"
            ),

            StoreInventory(
                id = "F1006",
                storeId = "F1",
                name = "The Eclair Bestie",
                price = 89000,
                imageRes = R.drawable.dummy,
                category = "The Eclair"
            ),

            StoreInventory(
                id = "F1007",
                storeId = "F1",
                name = "The Eclair",
                price = 49900,
                imageRes = R.drawable.dummy,
                category = "The Eclair"
            ),

            StoreInventory(
                id = "F1008",
                storeId = "F1",
                name = "Eclair Chocolate",
                price = 25000,
                imageRes = R.drawable.dummy,
                category = "The Eclair"
            ),

            StoreInventory(
                id = "F1009",
                storeId = "F1",
                name = "Eclair Vanilla",
                price = 25000,
                imageRes = R.drawable.dummy,
                category = "The Eclair"
            ),

            StoreInventory(
                id = "F1010",
                storeId = "F1",
                name = "Durian",
                price = 93000,
                imageRes = R.drawable.dummy,
                category = "Signature Pie Cream Puff"
            ),

            StoreInventory(
                id = "F1011",
                storeId = "F1",
                name = "Cookies & Cream",
                price = 75600,
                imageRes = R.drawable.dummy,
                category = "Signature Pie Cream Puff"
            ),

            StoreInventory(
                id = "F1012",
                storeId = "F1",
                name = "Vanilla Custard",
                price = 58000,
                imageRes = R.drawable.dummy,
                category = "Signature Pie Cream Puff"
            ),

            StoreInventory(
                id = "F1013",
                storeId = "F1",
                name = "Black Thorn",
                price = 98000,
                imageRes = R.drawable.dummy,
                category = "Classic Eclair"
            ),

            StoreInventory(
                id = "F1014",
                storeId = "F1",
                name = "Cookies & Cream",
                price = 82000,
                imageRes = R.drawable.dummy,
                category = "Classic Eclair"
            ),

            StoreInventory(
                id = "F1015",
                storeId = "F1",
                name = "Custard",
                price = 68000,
                imageRes = R.drawable.dummy,
                category = "Classic Eclair"
            ),

            StoreInventory(
                id = "F1016",
                storeId = "F1",
                name = "Black Thorn",
                price = 151700,
                imageRes = R.drawable.dummy,
                category = "Mini Cream Puff"
            ),

            StoreInventory(
                id = "F1017",
                storeId = "F1",
                name = "Vanilla Custard",
                price = 102000,
                imageRes = R.drawable.dummy,
                category = "Mini Cream Puff"
            ),

            StoreInventory(
                id = "F1018",
                storeId = "F1",
                name = "10 pcs",
                price = 220300,
                imageRes = R.drawable.dummy,
                category = "Mini Cream Puff"
            )
        )
    }
}