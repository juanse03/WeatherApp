package com.example.weatherapp.data.api

import com.example.weatherapp.domain.entities.ForecastWeatherEntity
import com.example.weatherapp.domain.repository.ForecastWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

class ForecastWeatherRepositoryImpl @Inject constructor(
    private val service: OpenWeatherApi
) : ForecastWeatherRepository {

    override suspend fun getForecastWeather(
        latitude: Double,
        longitude: Double,
        count: String
    ): Flow<ForecastWeatherEntity> {
        return flow {
            val data =
                service.getForecastWeather(latitude, longitude, cnt = count)
            emit(ForecastWeatherEntity(data))
        }
    }
}
