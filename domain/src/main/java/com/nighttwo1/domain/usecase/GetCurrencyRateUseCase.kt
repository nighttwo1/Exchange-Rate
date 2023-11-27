package com.nighttwo1.domain.usecase

import com.nighttwo1.domain.repository.CurrencyRepository
import java.util.*
import javax.inject.Inject

class GetCurrencyRateUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {
    operator fun invoke(from: Currency, to: Currency) = currencyRepository.getExchangeRate(from, to)
}