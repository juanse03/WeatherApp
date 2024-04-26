package com.example.weatherapp.ui.models

data class LandingScreenUIModel(
    val cityName: String,
    val tempSensation: Double,
    val tempMin: Double,
    val tempMax: Double,
    val windSpeed: Double,
    val windDirection: String,
    val forecast: List<DailyForecast>
)

data class DailyForecast(
    val icon: String,
    val temperature: Double,
    val time: String,
)
