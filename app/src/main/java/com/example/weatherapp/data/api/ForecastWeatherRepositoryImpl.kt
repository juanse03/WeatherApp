package com.example.weatherapp.data.api

import com.example.weatherapp.domain.entities.ForecastEntity
import com.example.weatherapp.domain.repository.ForecastWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ForecastWeatherRepositoryImpl @Inject constructor(
    private val service: OpenWeatherApi
) : ForecastWeatherRepository {

    override suspend fun getForecastWeather(
        latitude: Double,
        longitude: Double,
        count: String
    ): Flow<List<ForecastEntity>> {
        return flow {
            val response = mutableListOf<ForecastEntity>()
            val data =
                service.getForecastWeather(latitude, longitude, cnt = count)
            data.forecastList.map {
                response.add(ForecastEntity(it))
            }
            emit(response)
        }
    }
}
