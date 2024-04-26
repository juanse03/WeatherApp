package com.example.weatherapp.data.api

import com.example.weatherapp.domain.entities.CurrentWeatherEntity
import com.example.weatherapp.domain.repository.CurrentWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

class CurrentWeatherRepositoryImpl @Inject constructor(
    private val service: OpenWeatherApi
) : CurrentWeatherRepository {

    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): Flow<CurrentWeatherEntity> {
        return flow {
            val data = service.getCurrentWeather(latitude, longitude)
            emit(CurrentWeatherEntity(data))
        }
    }
}
