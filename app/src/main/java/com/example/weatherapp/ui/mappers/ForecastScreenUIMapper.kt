package com.example.weatherapp.ui.mappers

import com.example.weatherapp.domain.entities.CurrentWeatherEntity
import com.example.weatherapp.domain.entities.ForecastWeatherEntity
import com.example.weatherapp.ui.models.DailyForecast
import com.example.weatherapp.ui.models.ForecastScreenUIModel
import com.example.weatherapp.ui.models.ForecastUIModel
import com.example.weatherapp.ui.models.LandingScreenUIModel
import javax.inject.Inject

class ForecastScreenUIMapper @Inject constructor() {
    fun map(forecastWeather: ForecastWeatherEntity) : ForecastScreenUIModel {
        return ForecastScreenUIModel(
            forecastWeather.forecastList.map {
                ForecastUIModel(
                    it.maxTemp,
                    it.minTemp,
                    it.weatherName,
                    it.weatherIcon,
                    it.windSpeed,
                    it.windDirection,
                    it.date
                )
            }
        )
    }
}