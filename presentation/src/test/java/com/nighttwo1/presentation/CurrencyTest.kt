package com.nighttwo1.presentation

import org.junit.Test
import java.util.*

class CurrencyTest {
    @Test
    fun `check_currency_info`() {
        val kor = Currency.getInstance(Locale.KOREA)
        println("currencyCode ${kor.currencyCode}")
        println("numericCode ${kor.numericCode}")
        println("symbol ${kor.symbol}")
        println("displayName ${kor.displayName}")
        println("defaultFractionDigits ${kor.defaultFractionDigits}")
        println("AvailableCurrencies: ${Currency.getAvailableCurrencies()}")
    }
}