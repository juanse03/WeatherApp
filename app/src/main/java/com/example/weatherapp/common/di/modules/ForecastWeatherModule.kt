package com.example.weatherapp.common.di.modules

import com.example.weatherapp.data.api.ForecastWeatherRepositoryImpl
import com.example.weatherapp.domain.repository.ForecastWeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ForecastWeatherModule {

    @Binds
    @Reusable
    abstract fun provideForecastWeatherRepository(
        forecastRepositoryImpl: ForecastWeatherRepositoryImpl
    ): ForecastWeatherRepository
}
