package com.example.weatherapp.ui.models

data class ForecastUIModel(
    val maxTemp: Double,
    val minTemp: Double,
    val weatherName: String,
    val weatherIcon: String,
    val windSpeed: Double,
    val windDirection: String,
    val date: String,
)
