package com.example.weatherapp.ui.landingscreen

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.isDebugInspectorInfoEnabled
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.weatherapp.common.extensions.toTwelveHour
import com.example.weatherapp.ui.commonwidgets.ShowError
import com.example.weatherapp.ui.commonwidgets.ShowLoader
import com.example.weatherapp.ui.landingscreen.widgets.ShowCurrentWeather
import com.example.weatherapp.ui.landingscreen.widgets.ShowLocationPermissionRequest
import com.example.weatherapp.ui.models.DailyForecast
import com.example.weatherapp.ui.models.LandingScreenUIModel
import com.example.weatherapp.ui.state.CurrentWeatherState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterialApi::class)
@Composable
fun LandingScreen(
    viewModel: LandingScreenViewModel = hiltViewModel<LandingScreenViewModel>()
) {
    val locationPermissionState =
        rememberPermissionState(Manifest.permission.ACCESS_COARSE_LOCATION)
    val refreshing = remember {
        mutableStateOf(false)
    }
    val landingState = viewModel.landingScreenState.collectAsState()

    if (locationPermissionState.status.isGranted) {
        val fusedLocationProviderClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(LocalContext.current)
        GetLocation(landingState.value, fusedLocationProviderClient) { lat, lon ->
            viewModel.fetchWeather(lat, lon)
        }

        val pullRefreshState = rememberPullRefreshState(
            refreshing.value, viewModel::refresh
        )

        when (landingState.value) {
            is CurrentWeatherState.ShowCurrentWeather -> {
                refreshing.value = false
                ShowCurrentWeather(
                    Modifier.pullRefresh(pullRefreshState),
                    (landingState.value as CurrentWeatherState.ShowCurrentWeather).currentWeather
                )
            }

            is CurrentWeatherState.ShowError -> ShowError((landingState.value as CurrentWeatherState.ShowError).error)

            is CurrentWeatherState.ShowLoading -> {
                refreshing.value = true
                ShowLoader(
                    (landingState.value as CurrentWeatherState.ShowLoading).isLoading
                )
            }

            is CurrentWeatherState.RequestLocationPermission -> {}
        }
    } else if (landingState.value is CurrentWeatherState.RequestLocationPermission) {
        ShowLocationPermissionRequest(locationPermissionState, viewModel)
    }

    LaunchedEffect(
        locationPermissionState.status.isGranted ||
                locationPermissionState.status.shouldShowRationale || landingState.value is CurrentWeatherState.RequestLocationPermission
    ) {
        if (!locationPermissionState.status.isGranted && locationPermissionState.status.shouldShowRationale) {
            viewModel.request()
        } else {
            locationPermissionState.launchPermissionRequest()
        }
    }
}

@Composable
@SuppressLint("MissingPermission")
private fun GetLocation(
    state: CurrentWeatherState,
    fusedLocationProviderClient: FusedLocationProviderClient,
    onSuccess: (Double, Double) -> Unit
) {
    if (state is CurrentWeatherState.ShowLoading) {
        LaunchedEffect(state) {
            fusedLocationProviderClient.getCurrentLocation(
                Priority.PRIORITY_BALANCED_POWER_ACCURACY, CancellationTokenSource().token
            ).addOnSuccessListener {
                onSuccess.invoke(it.latitude, it.longitude)
            }
        }
    }
}
