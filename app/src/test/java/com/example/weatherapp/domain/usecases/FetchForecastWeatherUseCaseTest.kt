package com.example.weatherapp.domain.usecases

import com.example.weatherapp.data.models.forecast.Forecast
import com.example.weatherapp.data.models.forecast.ForecastTemperature
import com.example.weatherapp.data.models.forecast.ForecastWeatherApiResponse
import com.example.weatherapp.domain.entities.ForecastWeatherEntity
import com.example.weatherapp.domain.repository.ForecastWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class FetchForecastWeatherUseCaseTest {

    @Mock
    lateinit var repository: ForecastWeatherRepository

    private lateinit var sut: FetchForecastWeatherUseCase

    private val api = ForecastWeatherApiResponse(
        listOf(
            Forecast(
                21212,
                ForecastTemperature(2.0, 2.0, 2.0),
                listOf(com.example.weatherapp.data.models.forecast.Weather("sn", "1")),
                com.example.weatherapp.data.models.forecast.Wind(2.0, 1),
                "2024-04-26 12:00:00"
            )
        ),
    )

    private val response: Flow<ForecastWeatherEntity> = flow {
        ForecastWeatherEntity(api)
    }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        sut = FetchForecastWeatherUseCase(repository)

        runBlocking {
            whenever(
                repository.getForecastWeather(
                    2.0,
                    40.0,
                    "40"
                )
            ).thenReturn(response)
        }
    }

    @Test
    fun getForecastWeather(): Unit = runBlocking {
        sut.invoke(2.0, 40.0).runCatching {
            assert(this == response)
        }

        verify(repository).getForecastWeather(2.0, 40.0, "40")
    }
}