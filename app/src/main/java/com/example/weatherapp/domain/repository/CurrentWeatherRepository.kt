package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.entities.CurrentWeatherEntity
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepository {
    suspend fun getCurrentWeather(latitude: Double, longitude: Double): Flow<CurrentWeatherEntity>
}
