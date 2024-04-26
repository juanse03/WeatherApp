package com.example.weatherapp.ui.landingscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.usecases.FetchCurrentWeatherUseCase
import com.example.weatherapp.domain.usecases.FetchForecastWeatherUseCase
import com.example.weatherapp.ui.mappers.LandingScreenUIMapper
import com.example.weatherapp.ui.state.CurrentWeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingScreenViewModel @Inject constructor(
    private val mapper: LandingScreenUIMapper,
    private val fetchCurrentWeatherUseCase: FetchCurrentWeatherUseCase,
    private val fetchForecastWeatherUseCase: FetchForecastWeatherUseCase
) : ViewModel() {

    private val _landingScreenState =
        MutableStateFlow<CurrentWeatherState>(CurrentWeatherState.ShowLoading(true))
    val landingScreenState get() = _landingScreenState

    fun fetchWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            fetchCurrentWeatherUseCase.invoke(latitude, longitude).zip(
                fetchForecastWeatherUseCase.invoke(latitude, longitude, "3")
            ) { currentWeather, dailyForecast ->
                mapper.map(currentWeather, dailyForecast)
            }
                .flowOn(Dispatchers.IO)
                .catch {
                    _landingScreenState.value = CurrentWeatherState.ShowError(it.message.orEmpty())
                }
                .collect {
                    _landingScreenState.tryEmit(CurrentWeatherState.ShowCurrentWeather(it))
                }
        }
    }

    fun refresh() {
        _landingScreenState.value = CurrentWeatherState.ShowLoading(true)
    }

    fun request() {
        _landingScreenState.value = CurrentWeatherState.RequestLocationPermission()
    }
}