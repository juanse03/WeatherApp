package com.example.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class CurrentWeatherApiResponse(
    val weather: List<Weather>,
    @SerializedName("main")
    val temperature: Temperature,
    val wind: Wind,
    @SerializedName("dt")
    val time: Long,
    //val timezone: Int,
    @SerializedName("name")
    val cityName: String,
)

data class Weather(
    @SerializedName("main")
    val name: String,
    val icon: String
)

data class Temperature(
    val temp: Double,
    @SerializedName("feels_like")
    val tempSensation: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
)

data class Wind(
    val speed: Double,
    val deg: Int,
)