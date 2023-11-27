package com.nighttwo1.data.repository

import android.util.Log
import com.nighttwo1.data.adapter.networkAdapter
import com.nighttwo1.domain.model.ExchangeRate
import com.nighttwo1.domain.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.util.*

class CurrencyRepositoryImpl : CurrencyRepository {
    override fun getExchangeRate(from: Currency, to: Currency) = networkAdapter {
        Log.d("url", "https://www.google.com/finance/quote/$from-$to")
        val doc: Document =
            Jsoup.connect("https://www.google.com/finance/quote/$from-$to").get() //URL 웹사이트에 있는 html 코드를 다 끌어오기
        val exchangeRate = doc.getElementsByClass("YMlKec fxKbKc")

        Log.d("data exchangeRate", "${exchangeRate[0].text()}")

        ExchangeRate(from, to, exchangeRate[0].text().replace(",", "").toDouble())
    }.flowOn(Dispatchers.IO)
}