package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.api.ForecastWeatherRepositoryImpl
import com.example.weatherapp.data.api.OpenWeatherApi
import com.example.weatherapp.data.models.forecast.Forecast
import com.example.weatherapp.data.models.forecast.ForecastTemperature
import com.example.weatherapp.data.models.forecast.ForecastWeatherApiResponse
import com.example.weatherapp.data.models.forecast.Weather
import com.example.weatherapp.data.models.forecast.Wind
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ForecastWeatherRepositoryTest {

    @Mock
    lateinit var api: OpenWeatherApi

    private lateinit var sut: ForecastWeatherRepository

    private val response = ForecastWeatherApiResponse(
        listOf(
            Forecast(
                21212,
                ForecastTemperature(2.0, 2.0, 2.0),
                listOf(Weather("sn", "1")),
                Wind(2.0, 1),
                "2024-04-26 12:00:00"
            )
        ),
    )

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        sut = ForecastWeatherRepositoryImpl(api)

        runBlocking {
            whenever(api.getForecastWeather(2.0, 40.0, cnt = "40")).thenReturn(response)
        }
    }

    @Test
    fun getForecastWeather(): Unit = runBlocking {
        sut.getForecastWeather(2.0, 40.0, "40").runCatching {
            this.collect {
                assert(it == ForecastWeatherEntity(response))
            }
        }
        verify(api).getForecastWeather(2.0, 40.0, cnt = "40")
    }
}
