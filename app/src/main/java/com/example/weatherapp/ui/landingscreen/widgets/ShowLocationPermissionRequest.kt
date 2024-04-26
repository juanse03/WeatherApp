package com.example.weatherapp.ui.landingscreen.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.weatherapp.ui.landingscreen.LandingScreenViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ShowLocationPermissionRequest(
    locationPermissionState: PermissionState,
    viewModel: LandingScreenViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "We need location permission to work")
        Text(text = "Allow WeatherApp to access your location?")
        Button(onClick = {
            locationPermissionState.launchPermissionRequest()
            viewModel.refresh()
        }) {
            Text(text = "Allow")
        }
    }
}