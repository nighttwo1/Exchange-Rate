package com.nighttwo1.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.ExchangeRate
import com.nighttwo1.domain.usecase.GetLatestCurrencyRateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLatestCurrencyRate: GetLatestCurrencyRateUseCase
) : ViewModel() {
    val currencyFrom = MutableStateFlow<Currency>(Currency.getInstance(Locale.US))
    val currencyTo = MutableStateFlow<Currency>(Currency.getInstance(Locale.KOREA))

    private val _exchangeRateState: MutableStateFlow<NetworkResult<ExchangeRate>> =
        MutableStateFlow(NetworkResult.Ready())
    val exchangeRate: StateFlow<NetworkResult<ExchangeRate>> = _exchangeRateState

    fun getLatestExchangeRate() {
        viewModelScope.launch {
            getLatestCurrencyRate(currencyFrom.value, currencyTo.value).collectLatest {
                _exchangeRateState.emit(it)
            }
        }
    }
}