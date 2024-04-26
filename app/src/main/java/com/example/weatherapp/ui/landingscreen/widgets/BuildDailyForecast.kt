package com.example.weatherapp.ui.landingscreen.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapp.common.extensions.toTwelveHour
import com.example.weatherapp.ui.models.DailyForecast

@Composable
fun BuildDailyForecast(dailyForecast: DailyForecast) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color(0, 141, 117))
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                text = dailyForecast.time.toTwelveHour()
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "${dailyForecast.temperature.toInt()}° F"
            )
            AsyncImage(
                model = "https://openweathermap.org/img/wn/${dailyForecast.icon}@2x.png",
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                contentScale = ContentScale.FillWidth
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}