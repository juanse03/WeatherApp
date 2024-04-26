package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.repository.CurrentWeatherRepository
import javax.inject.Inject

class FetchCurrentWeatherUseCase @Inject constructor(
    private val repository: CurrentWeatherRepository
) {
    suspend operator fun invoke(latitude: Double, longitude: Double) = repository.getCurrentWeather(latitude, longitude)
}
