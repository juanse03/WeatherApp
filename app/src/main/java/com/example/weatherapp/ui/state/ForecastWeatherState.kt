package com.example.weatherapp.ui.state

import com.example.weatherapp.domain.entities.ForecastWeatherEntity
import com.example.weatherapp.ui.models.ForecastScreenUIModel

sealed class ForecastWeatherState {
    class ShowLoading(val isLoading: Boolean) : ForecastWeatherState()

    class ShowForecastWeather(val forecastWeather: ForecastScreenUIModel) :
        ForecastWeatherState()

    class ShowError(val error: String) : ForecastWeatherState()
}
