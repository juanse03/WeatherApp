package com.example.weatherapp.domain.entities

import android.os.Parcelable
import com.example.weatherapp.data.models.forecast.ForecastWeatherApiResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastWeatherEntity(
    val forecastList: List<ForecastEntity>
) : Parcelable
{
    constructor(forecastWeatherApiResponse: ForecastWeatherApiResponse) : this(
        forecastWeatherApiResponse.forecastList.map {
            ForecastEntity(it)
        }
    )
}




