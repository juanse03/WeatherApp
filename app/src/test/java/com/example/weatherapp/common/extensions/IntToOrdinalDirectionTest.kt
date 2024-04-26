package com.example.weatherapp.common.extensions

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotSame
import org.junit.Test

class IntToOrdinalDirectionTest {
    @Test
    fun testToOrdinalDirection() {
        assertEquals("N", 0.toOrdinalDirection())
        assertEquals("N", 360.toOrdinalDirection())
        assertEquals("NNE", 22.toOrdinalDirection())
        assertEquals("NE", 45.toOrdinalDirection())
        assertEquals("E", 90.toOrdinalDirection())
        assertEquals("ESE", 112.toOrdinalDirection())
        assertEquals("S", 180.toOrdinalDirection())
        assertEquals("WSW", 247.toOrdinalDirection())
        assertEquals("W", 270.toOrdinalDirection())
        assertEquals("WNW", 292.toOrdinalDirection())
        assertEquals("N", 3600.toOrdinalDirection())
        assertNotSame("N", 40.toOrdinalDirection())
    }
}