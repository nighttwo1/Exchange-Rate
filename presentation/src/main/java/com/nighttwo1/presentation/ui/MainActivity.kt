package com.nighttwo1.presentation.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.presentation.R
import com.nighttwo1.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mainViewModel.getExchangeRate()

        lifecycleScope.launch {
            mainViewModel.exchangeRate.collectLatest { exchangeRateResult ->
                if (exchangeRateResult is NetworkResult.Success) {
                    binding.exchangeRate.text = getString(
                        R.string.exchange_rate_inform_message,
                        exchangeRateResult.data.from,
                        toDecimalFormat(exchangeRateResult.data.rate),
                        exchangeRateResult.data.to
                    )

                    showAmountAfter(
                        calculateAmountAfter(
                            if (binding.amountFrom.text.isNullOrBlank()) 0.0 else binding.amountFrom.text.toString()
                                .toDouble()
                        )
                    )
                }
            }
        }
        binding.layoutAmountFrom.suffixText = mainViewModel.currencyFrom.value.currencyCode
        binding.layoutAmountTo.suffixText = mainViewModel.currencyTo.value.currencyCode

        binding.amountFrom.doAfterTextChanged { text ->
            showAmountAfter(
                calculateAmountAfter(if (text.isNullOrBlank()) 0.0 else text.toString().toDouble())
            )
        }

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.simple_items,
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.item.setAdapter(adapter)

        // Set an item selected listener for AutoCompleteTextView
        binding.item.setOnItemClickListener { parent, view, position, id ->
            // Handle the selected item
            val selectedItem = parent.getItemAtPosition(position).toString()
            // Do something with the selected item
            mainViewModel.currencyFrom.value = Currency.getInstance(selectedItem)
            binding.layoutAmountFrom.suffixText = mainViewModel.currencyFrom.value.currencyCode
            mainViewModel.getExchangeRate()
        }
    }

    private fun showAmountAfter(amountTo: String) {
        binding.amountTo.setText(amountTo)
    }

    private fun calculateAmountAfter(amountFrom: Double): String {
        return toDecimalFormat(
            (amountFrom) * (mainViewModel.exchangeRate.value as NetworkResult.Success).data.rate
        )
    }
}