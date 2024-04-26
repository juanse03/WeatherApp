package com.example.weatherapp.domain.entities

import android.os.Parcelable
import com.example.weatherapp.common.extensions.toOrdinalDirection
import com.example.weatherapp.data.models.forecast.Forecast
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastEntity(
    val temp: Double,
    val maxTemp: Double,
    val minTemp: Double,
    val weatherName: String,
    val weatherIcon: String,
    val windSpeed: Double,
    val windDirection: String,
    val date: String,
) : Parcelable {
    constructor(forecast: Forecast) : this(
        forecast.temperature.temp,
        forecast.temperature.tempMax,
        forecast.temperature.tempMin,
        forecast.weather.first().name,
        forecast.weather.first().icon,
        forecast.wind.speed,
        forecast.wind.windDirection.toOrdinalDirection(),
        forecast.time
    )
}