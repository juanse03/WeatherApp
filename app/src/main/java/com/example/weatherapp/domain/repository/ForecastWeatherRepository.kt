package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.entities.ForecastEntity
import kotlinx.coroutines.flow.Flow

interface ForecastWeatherRepository {
    suspend fun getForecastWeather(
        latitude: Double,
        longitude: Double,
        count: String
    ): Flow<List<ForecastEntity>>
}
