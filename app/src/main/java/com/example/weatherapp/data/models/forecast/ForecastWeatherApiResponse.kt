package com.example.weatherapp.data.models.forecast

import com.google.gson.annotations.SerializedName

data class ForecastWeatherApiResponse(
    @SerializedName("list")
    val forecastList: List<Forecast>,
)

data class Forecast(
    @SerializedName("dt")
    val date: Long,
    @SerializedName("main")
    val temperature: ForecastTemperature,
    val weather: List<Weather>,
    val wind: Wind,
    @SerializedName("dt_txt")
    val time: String
)

data class ForecastTemperature(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
)

data class Weather(
    @SerializedName("description")
    val name: String,
    val icon: String
)

data class Wind(
    val speed: Double,
    @SerializedName("deg")
    val windDirection: Int,
)



