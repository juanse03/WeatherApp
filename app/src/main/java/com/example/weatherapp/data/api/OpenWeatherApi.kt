package com.example.weatherapp.data.api

import com.example.weatherapp.common.constants.PUBLIC_KEY
import com.example.weatherapp.data.models.CurrentWeatherApiResponse
import com.example.weatherapp.data.models.forecast.ForecastWeatherApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {
    @GET("weather?units=imperial")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") key: String = PUBLIC_KEY,
    ): CurrentWeatherApiResponse

    @GET("forecast?units=imperial")
    suspend fun getForecastWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") key: String = PUBLIC_KEY,
        @Query("cnt") cnt: String,
    ): ForecastWeatherApiResponse
}
