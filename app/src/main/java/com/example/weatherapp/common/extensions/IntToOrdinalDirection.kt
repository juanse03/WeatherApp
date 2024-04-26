package com.example.weatherapp.common.extensions

fun Int.toOrdinalDirection(): String {
    val directions = arrayOf("N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW")
    val index = ((this / 22.5) + 0.5).toInt() % 16
    return directions[index]
}