package com.example.weatherapp.domain.entities

import android.os.Parcelable
import com.example.weatherapp.common.extensions.toOrdinalDirection
import com.example.weatherapp.data.models.CurrentWeatherApiResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentWeatherEntity(
    val weather: List<String>,
    val cityName: String,
    val tempSensation: Double,
    val tempMin: Double,
    val tempMax: Double,
    val iconName: List<String>,
    val windSpeed: Double,
    val windDirection: String,
    val time: Long
) : Parcelable {
    constructor(currentWeatherApiResponse: CurrentWeatherApiResponse) : this(
        currentWeatherApiResponse.weather.map {
            it.name
        },
        currentWeatherApiResponse.cityName,
        currentWeatherApiResponse.temperature.tempSensation,
        currentWeatherApiResponse.temperature.tempMin,
        currentWeatherApiResponse.temperature.tempMax,
        currentWeatherApiResponse.weather.map {
            it.icon
        },
        currentWeatherApiResponse.wind.speed,
        currentWeatherApiResponse.wind.deg.toOrdinalDirection(),
        currentWeatherApiResponse.time,
    )
}
