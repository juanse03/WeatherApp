package com.example.weatherapp.ui.forecastscreen.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import coil.compose.AsyncImage
import com.example.weatherapp.common.extensions.toDate
import com.example.weatherapp.ui.models.ForecastUIModel

@Composable
fun BuildForecast(forecast: ForecastUIModel, icon: String, weatherName: String) {
    Column(
        modifier = Modifier
            .background(Color(0, 141, 117))
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = forecast.date.toDate(),
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Hi Temp: ${forecast.maxTemp.toInt()}°",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Low Temp: ${forecast.minTemp.toInt()}°",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Wind Speed",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "${forecast.windSpeed} mph",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = forecast.windDirection,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                AsyncImage(
                    model = "https://openweathermap.org/img/wn/${icon}@2x.png",
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = weatherName,
                    style = TextStyle(fontSize = 16.sp)
                )
            }
        }
    }
}