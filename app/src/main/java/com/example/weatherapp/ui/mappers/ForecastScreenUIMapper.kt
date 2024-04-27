package com.example.weatherapp.ui.mappers

import com.example.weatherapp.common.extensions.toDay
import com.example.weatherapp.domain.entities.ForecastEntity
import com.example.weatherapp.ui.models.ForecastUIModel
import javax.inject.Inject

class ForecastScreenUIMapper @Inject constructor() {
    fun map(forecastWeather: List<ForecastEntity>) : List<ForecastUIModel> {
        val groupedWeather = forecastWeather.groupBy { it.date.toDay() }

        val updatedWeatherList = mutableListOf<ForecastEntity>()

        for ((_, group) in groupedWeather) {
            val mostCommonDescription = group.groupingBy { it.weatherName }.eachCount().maxByOrNull { it.value }!!.key
            val mostCommonIcon = group.groupingBy { it.weatherIcon }.eachCount().maxByOrNull { it.value }!!.key
            val updatedGroup = group.map { it.copy(weatherName = mostCommonDescription, weatherIcon = mostCommonIcon) }
            updatedWeatherList.addAll(updatedGroup)
        }

        return updatedWeatherList.map {
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
    }
}