package com.example.application.global.util

fun Int.toRupiah(): String {
    return "Rp%,d"
        .format(this)
        .replace(',', '.')
}