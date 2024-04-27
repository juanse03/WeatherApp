package com.example.weatherapp.ui.state

import com.example.weatherapp.ui.models.ForecastUIModel

sealed class ForecastWeatherState {
    class ShowLoading(val isLoading: Boolean) : ForecastWeatherState()

    class ShowForecastWeather(val forecastWeather: List<ForecastUIModel>) :
        ForecastWeatherState()

    class ShowError(val error: String) : ForecastWeatherState()
}
