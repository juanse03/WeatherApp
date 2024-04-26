package com.example.weatherapp.common.extensions

import org.junit.Assert.assertEquals
import org.junit.Test

class DateFormatterTest {

    @Test
    fun testToDate() {
        assertEquals("viernes, abril 26", "2024-04-26 12:00:00".toDate())
        assertEquals("", "invalid date".toDate())
    }

    @Test
    fun testToDay() {
        assertEquals("26", "2024-04-26 12:00:00".toDay())
        assertEquals("", "invalid date".toDay())
    }

    @Test
    fun testToTwelveHour() {
        assertEquals("01:00 p. m.", "13:00:00".toTwelveHour())
        assertEquals("", "invalid time".toTwelveHour())
    }
}