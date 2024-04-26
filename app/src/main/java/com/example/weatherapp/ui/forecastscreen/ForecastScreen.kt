package com.example.weatherapp.ui.forecastscreen

import android.annotation.SuppressLint
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.ui.commonwidgets.ShowError
import com.example.weatherapp.ui.commonwidgets.ShowLoader
import com.example.weatherapp.ui.forecastscreen.widgets.ShowForecastWeather
import com.example.weatherapp.ui.state.ForecastWeatherState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

@SuppressLint("MissingPermission")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ForecastScreen(
    viewModel: ForecastScreenViewModel = hiltViewModel<ForecastScreenViewModel>()
) {
    val refreshing = remember {
        mutableStateOf(false)
    }

    val forecastState = viewModel.forecastScreenState.collectAsState()
    val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(LocalContext.current)

    LaunchedEffect(forecastState.value) {
        fusedLocationProviderClient.getCurrentLocation(
            Priority.PRIORITY_BALANCED_POWER_ACCURACY,
            CancellationTokenSource().token
        ).addOnSuccessListener {
            viewModel.fetchForecast(it.latitude, it.longitude)
        }
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing.value,
        viewModel::refresh
    )

    when (forecastState.value) {
        is ForecastWeatherState.ShowForecastWeather -> {
            refreshing.value = false
            ShowForecastWeather(
                Modifier.pullRefresh(pullRefreshState),
                (forecastState.value as ForecastWeatherState.ShowForecastWeather).forecastWeather
            )
        }

        is ForecastWeatherState.ShowError ->
            ShowError((forecastState.value as ForecastWeatherState.ShowError).error)

        is ForecastWeatherState.ShowLoading -> {
            refreshing.value = true
            ShowLoader(
                (forecastState.value as ForecastWeatherState.ShowLoading).isLoading
            )
        }
    }
}
