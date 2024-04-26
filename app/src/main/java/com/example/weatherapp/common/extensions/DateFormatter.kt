package com.example.weatherapp.common.extensions

import com.example.weatherapp.common.constants.IN_PATTERN
import com.example.weatherapp.common.constants.IN_TIME_PATTERN
import com.example.weatherapp.common.constants.OUT_DAY_PATTERN
import com.example.weatherapp.common.constants.OUT_PATTERN
import com.example.weatherapp.common.constants.OUT_TWELVE_HOUR_PATTERN
import java.text.SimpleDateFormat
import java.util.Locale

fun String.toDate(): String {
    return try {
        val inFormat =
            SimpleDateFormat(IN_PATTERN, Locale.US).parse(this)
        SimpleDateFormat(OUT_PATTERN, Locale.getDefault()).format(inFormat!!)
    } catch (e: Exception) {
        ""
    }
}

fun String.toDay(): String {
    return try {
        val inFormat =
            SimpleDateFormat(IN_PATTERN, Locale.US).parse(this)
        SimpleDateFormat(OUT_DAY_PATTERN, Locale.getDefault()).format(inFormat!!)
    } catch (e: Exception) {
        ""
    }
}

fun String.toTwelveHour(): String {
    return try {
        val inFormat =
            SimpleDateFormat(IN_TIME_PATTERN, Locale.US).parse(this)
        SimpleDateFormat(OUT_TWELVE_HOUR_PATTERN, Locale.getDefault()).format(inFormat!!)
    } catch (e: Exception) {
        ""
    }
}
