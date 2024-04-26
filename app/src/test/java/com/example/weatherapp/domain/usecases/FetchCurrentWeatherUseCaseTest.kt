package com.example.weatherapp.domain.usecases

import com.example.weatherapp.data.models.CurrentWeatherApiResponse
import com.example.weatherapp.data.models.Temperature
import com.example.weatherapp.data.models.Weather
import com.example.weatherapp.data.models.Wind
import com.example.weatherapp.domain.entities.CurrentWeatherEntity
import com.example.weatherapp.domain.repository.CurrentWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class FetchCurrentWeatherUseCaseTest {

    @Mock
    lateinit var repository: CurrentWeatherRepository

    private lateinit var sut: FetchCurrentWeatherUseCase

    private val api = CurrentWeatherApiResponse(
        listOf(Weather("Sun","1")),
        Temperature(1.0, 1.0, 1.0, 1.0),
        Wind(1.0,1),
        10192,
        "Pop"
    )

    private val response: Flow<CurrentWeatherEntity> = flow {
        CurrentWeatherEntity(api)
    }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        sut = FetchCurrentWeatherUseCase(repository)

        runBlocking { whenever(repository.getCurrentWeather(2.0, 40.0)).thenReturn(response) }
    }

    @Test
    fun getCurrentWeather(): Unit = runBlocking {
        sut.invoke(2.0,40.0).runCatching {
            assert(this == response)
        }

        verify(repository).getCurrentWeather(2.0, 40.0)
    }
}