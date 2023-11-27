package com.nighttwo1.currency.di

import com.nighttwo1.data.repository.CurrencyRepositoryImpl
import com.nighttwo1.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideCurrencyRepository(): CurrencyRepository{
        return CurrencyRepositoryImpl()
    }
}