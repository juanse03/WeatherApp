package com.example.weatherapp.ui.forecastscreen.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.models.ForecastUIModel

@Composable
fun ShowForecastWeather(modifier: Modifier, forecastList: List<ForecastUIModel>) {
    LazyColumn(
        modifier.background(Color.LightGray),
    ) {
        items(forecastList) { forecast ->
            BuildForecast(forecast)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
