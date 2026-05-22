package com.example.application.delivery.data.repository

import com.example.application.R
import com.example.application.delivery.data.model.Store
import com.example.application.delivery.data.model.StoreInventory

class StoreRepository {

    fun getStore(): List<Store> {
        return listOf(

            //FOOD STORE
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

            //RETAIL STORE
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
            ),

            Store(
                id = "F6",
                name = "McDonald's",
                address = "Jl. Slamet Riyadi No.245, Surakarta",
                promo = "Paket hemat mulai 25rb",
                rating = 4.7,
                imageRes = R.drawable.dummy,
                openTime = "00:00",
                closeTime = "23:59",
                openDays = "Daily",
                tags = listOf("Burger", "Fast Food")
            ),

            Store(
                id = "F7",
                name = "Kopi Kenangan",
                address = "Solo Square Mall",
                promo = "Buy 1 Get 1 Coffee",
                rating = 4.6,
                imageRes = R.drawable.dummy,
                openTime = "08:00",
                closeTime = "22:00",
                openDays = "Daily",
                tags = listOf("Coffee", "Beverage")
            ),

            Store(
                id = "F8",
                name = "Sushi Tei",
                address = "Solo Paragon Mall",
                promo = "Salmon Matsuri",
                rating = 4.9,
                imageRes = R.drawable.dummy,
                openTime = "10:00",
                closeTime = "22:00",
                openDays = "Daily",
                tags = listOf("Japanese", "Sushi")
            ),

            Store(
                id = "F9",
                name = "Bakmi GM",
                address = "The Park Solo Baru",
                promo = "Gratis pangsit goreng",
                rating = 4.5,
                imageRes = R.drawable.dummy,
                openTime = "09:00",
                closeTime = "21:00",
                openDays = "Daily",
                tags = listOf("Noodles", "Chinese Food")
            ),

            Store(
                id = "F10",
                name = "Starbucks",
                address = "Solo Grand Mall",
                promo = "Happy Hour Frappuccino",
                rating = 4.8,
                imageRes = R.drawable.dummy,
                openTime = "07:00",
                closeTime = "23:00",
                openDays = "Daily",
                tags = listOf("Coffee", "Cafe")
            ),

            // RETAIL STORE
            Store(
                id = "R6",
                name = "Ace Hardware",
                address = "Solo Paragon Mall",
                promo = "Diskon perlengkapan rumah",
                rating = 4.8,
                imageRes = R.drawable.dummy,
                openTime = "10:00",
                closeTime = "22:00",
                openDays = "Daily",
                tags = listOf("Tools", "Household")
            ),

            Store(
                id = "R7",
                name = "Informa",
                address = "The Park Solo Baru",
                promo = "Furniture Sale",
                rating = 4.7,
                imageRes = R.drawable.dummy,
                openTime = "10:00",
                closeTime = "22:00",
                openDays = "Daily",
                tags = listOf("Furniture", "Home Living")
            ),

            Store(
                id = "R8",
                name = "iBox",
                address = "Solo Square Mall",
                promo = "Trade-in iPhone",
                rating = 4.9,
                imageRes = R.drawable.dummy,
                openTime = "10:00",
                closeTime = "21:00",
                openDays = "Daily",
                tags = listOf("Apple", "Electronics")
            ),

            Store(
                id = "R9",
                name = "Guardian",
                address = "Solo Grand Mall",
                promo = "Buy 2 Get 1 Skincare",
                rating = 4.5,
                imageRes = R.drawable.dummy,
                openTime = "09:00",
                closeTime = "22:00",
                openDays = "Daily",
                tags = listOf("Health", "Beauty")
            ),

            Store(
                id = "R10",
                name = "Miniso",
                address = "Solo Paragon Mall",
                promo = "Promo serba lucu",
                rating = 4.6,
                imageRes = R.drawable.dummy,
                openTime = "10:00",
                closeTime = "22:00",
                openDays = "Daily",
                tags = listOf("Lifestyle", "Accessories")
            )
        )
    }

    fun getStoreInventory(): List<StoreInventory> {
        return listOf(

            //F1
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
            ),

            // F6 - McDonald's
            StoreInventory(
                id = "F6001",
                storeId = "F6",
                name = "Big Mac",
                price = 45000,
                imageRes = R.drawable.dummy,
                category = "Burger"
            ),

            StoreInventory(
                id = "F6002",
                storeId = "F6",
                name = "McChicken",
                price = 38000,
                imageRes = R.drawable.dummy,
                category = "Burger"
            ),

            StoreInventory(
                id = "F6003",
                storeId = "F6",
                name = "French Fries Large",
                price = 25000,
                imageRes = R.drawable.dummy,
                category = "Sides"
            ),

            StoreInventory(
                id = "F6004",
                storeId = "F6",
                name = "Coca Cola",
                price = 12000,
                imageRes = R.drawable.dummy,
                category = "Drinks"
            ),

            // F7 - Kopi Kenangan
            StoreInventory(
                id = "F7001",
                storeId = "F7",
                name = "Kopi Kenangan Mantan",
                price = 28000,
                imageRes = R.drawable.dummy,
                category = "Coffee"
            ),

            StoreInventory(
                id = "F7002",
                storeId = "F7",
                name = "Americano",
                price = 22000,
                imageRes = R.drawable.dummy,
                category = "Coffee"
            ),

            StoreInventory(
                id = "F7003",
                storeId = "F7",
                name = "Milk Tea",
                price = 25000,
                imageRes = R.drawable.dummy,
                category = "Non Coffee"
            ),

            // F8 - Sushi Tei
            StoreInventory(
                id = "F8001",
                storeId = "F8",
                name = "Salmon Sushi",
                price = 52000,
                imageRes = R.drawable.dummy,
                category = "Sushi"
            ),

            StoreInventory(
                id = "F8002",
                storeId = "F8",
                name = "California Roll",
                price = 48000,
                imageRes = R.drawable.dummy,
                category = "Roll"
            ),

            StoreInventory(
                id = "F8003",
                storeId = "F8",
                name = "Chicken Katsu Don",
                price = 58000,
                imageRes = R.drawable.dummy,
                category = "Rice Bowl"
            ),

            // F9 - Bakmi GM
            StoreInventory(
                id = "F9001",
                storeId = "F9",
                name = "Bakmi Special",
                price = 35000,
                imageRes = R.drawable.dummy,
                category = "Noodles"
            ),

            StoreInventory(
                id = "F9002",
                storeId = "F9",
                name = "Pangsit Goreng",
                price = 28000,
                imageRes = R.drawable.dummy,
                category = "Sides"
            ),

            StoreInventory(
                id = "F9003",
                storeId = "F9",
                name = "Es Teh Manis",
                price = 10000,
                imageRes = R.drawable.dummy,
                category = "Drinks"
            ),

            // F10 - Starbucks
            StoreInventory(
                id = "F10001",
                storeId = "F10",
                name = "Caramel Macchiato",
                price = 55000,
                imageRes = R.drawable.dummy,
                category = "Coffee"
            ),

            StoreInventory(
                id = "F10002",
                storeId = "F10",
                name = "Java Chip Frappuccino",
                price = 62000,
                imageRes = R.drawable.dummy,
                category = "Frappuccino"
            ),

            StoreInventory(
                id = "F10003",
                storeId = "F10",
                name = "Cheesecake",
                price = 38000,
                imageRes = R.drawable.dummy,
                category = "Dessert"
            ),

            // R6 - Ace Hardware
            StoreInventory(
                id = "R6001",
                storeId = "R6",
                name = "Cordless Drill",
                price = 799000,
                imageRes = R.drawable.dummy,
                category = "Tools"
            ),

            StoreInventory(
                id = "R6002",
                storeId = "R6",
                name = "LED Desk Lamp",
                price = 199000,
                imageRes = R.drawable.dummy,
                category = "Lighting"
            ),

            // R7 - Informa
            StoreInventory(
                id = "R7001",
                storeId = "R7",
                name = "Office Chair",
                price = 1299000,
                imageRes = R.drawable.dummy,
                category = "Furniture"
            ),

            StoreInventory(
                id = "R7002",
                storeId = "R7",
                name = "Study Desk",
                price = 999000,
                imageRes = R.drawable.dummy,
                category = "Furniture"
            ),

            // R8 - iBox
            StoreInventory(
                id = "R8001",
                storeId = "R8",
                name = "iPhone 15",
                price = 15999000,
                imageRes = R.drawable.dummy,
                category = "Smartphone"
            ),

            StoreInventory(
                id = "R8002",
                storeId = "R8",
                name = "AirPods Pro",
                price = 3499000,
                imageRes = R.drawable.dummy,
                category = "Accessories"
            ),

            // R9 - Guardian
            StoreInventory(
                id = "R9001",
                storeId = "R9",
                name = "Skintific Cleanser",
                price = 89000,
                imageRes = R.drawable.dummy,
                category = "Skincare"
            ),

            StoreInventory(
                id = "R9002",
                storeId = "R9",
                name = "Vitamin C",
                price = 65000,
                imageRes = R.drawable.dummy,
                category = "Health"
            ),

            // R10 - Miniso
            StoreInventory(
                id = "R10001",
                storeId = "R10",
                name = "Cute Water Bottle",
                price = 59000,
                imageRes = R.drawable.dummy,
                category = "Lifestyle"
            ),

            StoreInventory(
                id = "R10002",
                storeId = "R10",
                name = "Bluetooth Speaker",
                price = 149000,
                imageRes = R.drawable.dummy,
                category = "Electronics"
            )
        )
    }
}