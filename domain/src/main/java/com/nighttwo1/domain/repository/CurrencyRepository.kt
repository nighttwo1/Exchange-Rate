package com.nighttwo1.domain.repository

import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.ExchangeRate
import kotlinx.coroutines.flow.Flow
import java.util.*

interface CurrencyRepository {
    fun getExchangeRate(from: Currency, to: Currency): Flow<NetworkResult<ExchangeRate>>
}