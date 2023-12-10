package com.nighttwo1.presentation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.ExchangeRate
import com.nighttwo1.domain.usecase.GetLatestCurrencyRateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ExchangeRateViewModel @Inject constructor(
    private val getLatestCurrencyRate: GetLatestCurrencyRateUseCase
) : ViewModel() {
    val currencyFrom = mutableStateOf(Currency.getInstance(Locale.US))
    val currencyTo = mutableStateOf(Currency.getInstance(Locale.KOREA))

    val _exchangeRate: MutableStateFlow<NetworkResult<ExchangeRate>> = MutableStateFlow(NetworkResult.Ready())
    val exchangeRate = _exchangeRate.asStateFlow()

    fun getExchangeRate() {
        viewModelScope.launch {
//            _exchangeRate.emit(NetworkResult.Ready())
            getLatestCurrencyRate(currencyFrom.value, currencyTo.value).collect { result ->
                _exchangeRate.emit(result)
            }
        }
    }
}
