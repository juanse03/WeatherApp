package com.example.weatherapp.ui.forecastscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.usecases.FetchForecastWeatherUseCase
import com.example.weatherapp.ui.mappers.ForecastScreenUIMapper
import com.example.weatherapp.ui.state.CurrentWeatherState
import com.example.weatherapp.ui.state.ForecastWeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastScreenViewModel @Inject constructor(
    private val mapper: ForecastScreenUIMapper,
    private val fetchForecastWeatherUseCase: FetchForecastWeatherUseCase
) : ViewModel() {

    private val _forecastScreenState =
        MutableStateFlow<ForecastWeatherState>(ForecastWeatherState.ShowLoading(true))
    val forecastScreenState get() = _forecastScreenState

    fun fetchForecast(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            fetchForecastWeatherUseCase.invoke(latitude, longitude)
                .flowOn(Dispatchers.IO)
                .catch {
                    _forecastScreenState.value = ForecastWeatherState.ShowError(it.message.orEmpty())
                }
                .collect {
                    _forecastScreenState.tryEmit(ForecastWeatherState.ShowForecastWeather(mapper.map(it)))
                }
        }
    }

    fun refresh(){
        _forecastScreenState.value = ForecastWeatherState.ShowLoading(true)    }
}