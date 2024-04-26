package com.example.weatherapp.ui.forecastscreen.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImage
import com.example.weatherapp.common.extensions.toDate
import com.example.weatherapp.common.extensions.toDay
import com.example.weatherapp.ui.forecastscreen.ForecastScreenViewModel
import com.example.weatherapp.ui.models.ForecastScreenUIModel
import com.example.weatherapp.ui.models.ForecastUIModel

@Composable
fun ShowForecastWeather(modifier: Modifier, forecastList: ForecastScreenUIModel, viewModel: ForecastScreenViewModel) {
    LazyColumn(
        modifier.background(Color.LightGray),
    ) {
        items(forecastList.forecast) { forecast ->
            val (icon, weatherName) = viewModel.forecastFilter(forecastList, forecast)
            BuildForecast(forecast, icon.orEmpty(), weatherName.orEmpty())
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
