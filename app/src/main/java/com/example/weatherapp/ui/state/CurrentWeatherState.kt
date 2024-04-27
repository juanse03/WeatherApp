package com.example.weatherapp.ui.state

import com.example.weatherapp.ui.models.LandingScreenUIModel

sealed class CurrentWeatherState {
    class ShowLoading(val isLoading: Boolean) : CurrentWeatherState()
    class ShowCurrentWeather(val currentWeather: LandingScreenUIModel) : CurrentWeatherState()
    class ShowError(val error: String) : CurrentWeatherState()
    class RequestLocationPermission() : CurrentWeatherState()
}
