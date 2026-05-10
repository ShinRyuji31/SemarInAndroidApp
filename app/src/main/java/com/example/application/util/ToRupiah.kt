package com.example.application.util

fun Int.toRupiah(): String {
    return "Rp%,d"
        .format(this)
        .replace(',', '.')
}