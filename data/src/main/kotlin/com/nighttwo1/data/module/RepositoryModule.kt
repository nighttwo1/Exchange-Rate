package com.nighttwo1.data.module

import com.nighttwo1.data.repository.CurrencyRepositoryImpl
import com.nighttwo1.domain.repository.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface BindRepositoryModule {
    @Binds
    @ViewModelScoped
    fun bindCurrencyRepository(currencyRepository: CurrencyRepositoryImpl): CurrencyRepository
}