package com.example.weatherapp.ui.landingscreen.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.models.LandingScreenUIModel

@Composable
fun ShowCurrentWeather(modifier: Modifier = Modifier, currentWeather: LandingScreenUIModel) {
    Column(
        modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = currentWeather.cityName,
            style = TextStyle(fontSize = 25.sp)
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Wind", style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold))
            Text(text = "Feels like: ${currentWeather.tempSensation.toInt()}°")
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = currentWeather.windDirection)
            Text(text = "${currentWeather.tempMax.toInt()}°/${currentWeather.tempMin.toInt()}°")
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "${currentWeather.windSpeed.toInt()} mph")
        }
        Spacer(modifier = Modifier.height(32.dp))
        LazyColumn {
            items(currentWeather.forecast) { dailyForecast ->
                BuildDailyForecast(dailyForecast)
            }
        }
    }
}