package com.example.appveterinaria.util

import java.text.NumberFormat
import java.util.Locale
import kotlin.math.roundToLong

fun Int.clp(): String {
    val formato = NumberFormat.getNumberInstance(Locale("es", "CL"))
    return "$ ${formato.format(this)}"
}

fun Double.clp(): String {
    val formato = NumberFormat.getNumberInstance(Locale("es", "CL"))
    return "$ ${formato.format(this.roundToLong())}"
}