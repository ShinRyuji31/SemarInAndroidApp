package com.example.application._core.util

import java.text.NumberFormat
import java.util.Locale

fun Int.toRupiah(): String {
    val localeID = Locale("in", "ID")
    val format = NumberFormat.getCurrencyInstance(localeID)
    return format.format(this.toDouble())
}

fun Long.toRupiah(): String {
    val localeID = Locale("in", "ID")
    val format = NumberFormat.getCurrencyInstance(localeID)
    return format.format(this.toDouble())
}

fun String.toRupiah(): String {
    val amount = this.toDoubleOrNull() ?: 0.0
    val localeID = Locale("in", "ID")
    val format = NumberFormat.getCurrencyInstance(localeID)
    return format.format(amount)
}
