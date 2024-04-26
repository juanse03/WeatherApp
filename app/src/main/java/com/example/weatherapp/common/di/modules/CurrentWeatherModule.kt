package com.example.weatherapp.common.di.modules

import com.example.weatherapp.domain.repository.CurrentWeatherRepository
import com.example.weatherapp.data.api.CurrentWeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CurrentWeatherModule {

    @Binds
    @Reusable
    abstract fun provideCurrentWeatherRepository(
        currentWeatherRepositoryImpl: CurrentWeatherRepositoryImpl
    ): CurrentWeatherRepository
}
