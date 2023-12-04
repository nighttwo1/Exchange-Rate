package com.nighttwo1.data.repository

import com.nighttwo1.data.adapter.networkAdapter
import com.nighttwo1.data.models.toDomain
import com.nighttwo1.data.service.CurrencyService
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.ExchangeRate
import com.nighttwo1.domain.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.util.*
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyService: CurrencyService
) : CurrencyRepository {
    override fun getCurrencyRate(from: Currency, to: Currency): Flow<NetworkResult<ExchangeRate>> = networkAdapter {
        when (from) {
            Currency.getInstance("EUR") -> {
                currencyService.getLatestCurrencyRate(to = to.currencyCode).toDomain(to)
            }

            else -> {
                val doc: Document =
                    Jsoup.connect("https://www.google.com/finance/quote/$from-$to").get() //URL 웹사이트에 있는 html 코드를 다 끌어오기
                val exchangeRate = doc.getElementsByClass("YMlKec fxKbKc")

                ExchangeRate(from, to, exchangeRate[0].text().replace(",", "").toDouble())
            }
        }
    }.flowOn(Dispatchers.IO)
}