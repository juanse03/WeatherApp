package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.api.CurrentWeatherRepositoryImpl
import com.example.weatherapp.data.api.OpenWeatherApi
import com.example.weatherapp.data.models.CurrentWeatherApiResponse
import com.example.weatherapp.data.models.Temperature
import com.example.weatherapp.data.models.Weather
import com.example.weatherapp.data.models.Wind
import com.example.weatherapp.domain.entities.CurrentWeatherEntity
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CurrentWeatherRepositoryTest {

    @Mock
    lateinit var api: OpenWeatherApi

    private lateinit var sut: CurrentWeatherRepository

    private val response = CurrentWeatherApiResponse(
        listOf(Weather("Sun", "1")),
        Temperature(1.0, 1.0, 1.0, 1.0),
        Wind(1.0, 1),
        10192,
        "Pop"
    )

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        sut = CurrentWeatherRepositoryImpl(api)

        runBlocking {
            whenever(api.getCurrentWeather(2.0, 40.0)).thenReturn(response)
        }
    }

    @Test
    fun getCurrentWeather(): Unit = runBlocking {
        sut.getCurrentWeather(2.0, 40.0).runCatching {
            this.collect {
                assert(it == CurrentWeatherEntity(response))
            }
        }
        verify(api).getCurrentWeather(2.0, 40.0)
    }
}
