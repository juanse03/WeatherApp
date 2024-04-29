package com.example.weatherapp.ui.mappers

import com.example.weatherapp.domain.entities.ForecastEntity
import com.example.weatherapp.ui.models.ForecastUIModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ForecastScreenUIMapperTest {

    private lateinit var sut: ForecastScreenUIMapper

    private var forecastWeather = listOf(
        ForecastEntity(
            20.0,
            25.0,
            15.0,
            "Cloudy",
            "cloud",
            10.0,
            "NE",
            "2024-04-29 00:00:00"
        ),
        ForecastEntity(
            20.0,
            25.0,
            15.0,
            "Sunny",
            "sun",
            10.0,
            "NE",
            "2024-04-29 00:00:00"
        ),
        ForecastEntity(
            20.0,
            25.0,
            15.0,
            "Sunny",
            "sun",
            10.0,
            "NE",
            "2024-04-29 00:00:00"
        ),
        ForecastEntity(
            20.0,
            25.0,
            15.0,
            "Cloudy",
            "cloud",
            10.0,
            "NE",
            "2024-05-01 00:00:00"
        ),
    )

    @Before
    fun setup() {
        sut = ForecastScreenUIMapper()
    }

    @Test
    fun map_CorrectlyMapsEntitiesToUIModels() {
        // Expected UI models
        val expectedUIModel1 = ForecastUIModel(25.0, 15.0, "Sunny", "sun", 10.0, "NE", "2024-04-29 00:00:00")
        val expectedUIModel2 = ForecastUIModel(25.0, 15.0, "Cloudy", "cloud", 10.0, "NE", "2024-05-01 00:00:00")

        // Map entities to UI models
        val result = sut.map(forecastWeather)

        // Verify
        assertEquals(4, result.size)
        assertEquals(expectedUIModel1, result[0])
        assertEquals(expectedUIModel2, result[3])
    }
}