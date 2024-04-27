package com.example.weatherapp.ui.mappers

import com.example.weatherapp.domain.entities.CurrentWeatherEntity
import com.example.weatherapp.domain.entities.ForecastEntity
import com.example.weatherapp.ui.models.DailyForecast
import com.example.weatherapp.ui.models.LandingScreenUIModel
import javax.inject.Inject

class LandingScreenUIMapper @Inject constructor() {
    fun map(currentWeather: CurrentWeatherEntity, forecastWeather: List<ForecastEntity>) :LandingScreenUIModel {
        return LandingScreenUIModel(
            currentWeather.cityName,
            currentWeather.tempSensation,
            currentWeather.tempMin,
            currentWeather.tempMax,
            currentWeather.windSpeed,
            currentWeather.windDirection,
            forecastWeather.map {
                DailyForecast(it.weatherIcon, it.temp, it.date.split(" ")[1])
            }
        )
    }
}