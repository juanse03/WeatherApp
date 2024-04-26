package com.example.weatherapp.domain.usecases

import com.example.weatherapp.common.constants.DEFAULT_COUNT
import com.example.weatherapp.domain.repository.ForecastWeatherRepository
import javax.inject.Inject

class FetchForecastWeatherUseCase @Inject constructor(
    private val repository: ForecastWeatherRepository
) {
    suspend operator fun invoke(
        latitude: Double, longitude: Double, count: String = DEFAULT_COUNT
    ) = repository.getForecastWeather(latitude,longitude,count)
}
