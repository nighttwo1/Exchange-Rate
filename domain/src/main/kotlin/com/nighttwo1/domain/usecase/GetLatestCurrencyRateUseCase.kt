package com.nighttwo1.domain.usecase

import com.nighttwo1.domain.repository.CurrencyRepository
import java.util.*
import javax.inject.Inject

class GetLatestCurrencyRateUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    operator fun invoke(from: Currency, to: Currency) = repository.getCurrencyRate(from, to)
}