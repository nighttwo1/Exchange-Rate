package com.nighttwo1.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.presentation.CurrencyIcons
import com.nighttwo1.presentation.component.CurrencyBasicTextField
import com.nighttwo1.presentation.component.DropdownMenu
import com.nighttwo1.presentation.component.LoadingSpinner
import com.nighttwo1.presentation.component.rememberDropdownMenuStatus
import com.nighttwo1.presentation.currencyicons.Bg
import com.nighttwo1.presentation.currencyicons.IcExchange
import com.nighttwo1.presentation.theme.CurrencyTheme
import java.util.*

@Composable
fun ExchangeRatePage(viewModel: ExchangeRateViewModel = hiltViewModel()) {
    val currencyFrom = viewModel.currencyFrom
    val currencyTo = viewModel.currencyTo
    val exchangeRate by viewModel.exchangeRate.collectAsState()

    LaunchedEffect(key1 = currencyFrom.value, key2 = currencyTo.value) {
        viewModel.getExchangeRate()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        ExchangeRateContent(
            currencyFrom = currencyFrom,
            currencyTo = currencyTo,
            exchangeRate = (exchangeRate as? NetworkResult.Success)?.data?.rate ?: -1.0
        )

        if (exchangeRate is NetworkResult.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x80cdcdcd)),
                contentAlignment = Alignment.Center
            ) {
                LoadingSpinner()
            }
        }
    }
}

@Composable
fun ExchangeRateContent(
    currencyFrom: MutableState<Currency>, currencyTo: MutableState<Currency>, exchangeRate: Double
) {
    val currencyList = remember { Currency.getAvailableCurrencies().toList() }
    var amountToChange by remember { mutableStateOf(0.0) }

    val currencyListFromStatus = rememberDropdownMenuStatus(
        selectedItemIndex = currencyList.indexOf(currencyFrom.value),
        options = Currency.getAvailableCurrencies().toList().map { it ->
            it.currencyCode
        }
    )
    LaunchedEffect(currencyListFromStatus.selectedItemIndex) {
        currencyFrom.value = currencyList.get(currencyListFromStatus.selectedItemIndex)
    }

    val currencyListToStatus = rememberDropdownMenuStatus(
        selectedItemIndex = currencyList.indexOf(currencyTo.value),
        options = Currency.getAvailableCurrencies().toList().map { it ->
            it.currencyCode
        }
    )
    LaunchedEffect(currencyListToStatus.selectedItemIndex) {
        currencyTo.value = currencyList.get(currencyListToStatus.selectedItemIndex)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                imageVector = CurrencyIcons.Bg,
                contentScale = ContentScale.FillWidth,
                contentDescription = "Bg",
            )
        }
        Column(modifier = Modifier.fillMaxSize().padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Currency Converter",
                fontSize = 25.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF1F2261),
                textAlign = TextAlign.Center,
            )

            Text(
                modifier = Modifier.padding(top = 10.dp, bottom = 41.dp),
                text = "Check live rates, set rate alerts, receive notifications and more.",
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF808080),
                textAlign = TextAlign.Center,
            )

            Column(
                modifier = Modifier
                    .shadow(elevation = 4.dp, spotColor = Color(0x0D000000), ambientColor = Color(0x0D000000))
                    .fillMaxWidth()
                    .height(268.dp)
                    .background(
                        color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 20.dp)
                    )
                    .padding(20.dp)
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 14.dp),
                    text = "Amount",
                    fontSize = 15.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF989898)
                )
                Row(
                    modifier = Modifier.fillMaxWidth().height(45.dp),
                    horizontalArrangement = Arrangement.spacedBy(13.dp)
                ) {
                    DropdownMenu(
                        modifier = Modifier.weight(0.5f),
                        dropdownMenuStatus = currencyListFromStatus,
                    )
                    CurrencyBasicTextField(
                        modifier = Modifier
                            .weight(0.5f)
                            .height(45.dp)
                            .background(
                                color = Color(0xFFEFEFEF),
                                shape = RoundedCornerShape(size = 7.dp)
                            ),
                        textStyle = TextStyle(textAlign = TextAlign.End),
                        value = amountToChange.toString(),
                        onValueChange = { amountToChange = it.toDoubleOrNull() ?: 0.0 }
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Box(
                    modifier = Modifier,
                    contentAlignment = Alignment.Center
                ) {
                    Divider(modifier = Modifier.fillMaxWidth().height(1.dp))
                    Icon(
                        modifier = Modifier.size(44.dp),
                        imageVector = CurrencyIcons.IcExchange,
                        contentDescription = "IcExchange",
                        tint = if (exchangeRate != -1.0) Color.Unspecified else Color.LightGray
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    modifier = Modifier.padding(bottom = 14.dp),
                    text = "Converted Amount",
                    fontSize = 15.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF989898)
                )
                Row(
                    modifier = Modifier.fillMaxWidth().height(45.dp),
                    horizontalArrangement = Arrangement.spacedBy(13.dp)
                ) {
                    DropdownMenu(
                        modifier = Modifier.weight(0.5f),
                        currencyListToStatus
                    )
                    CurrencyBasicTextField(
                        modifier = Modifier
                            .weight(0.5f)
                            .height(45.dp)
                            .background(
                                color = Color(0xFFEFEFEF),
                                shape = RoundedCornerShape(size = 7.dp)
                            ),
                        readOnly = true,
                        textStyle = TextStyle(textAlign = TextAlign.End),
                        value = toDecimalFormat(exchangeRate * amountToChange)
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Indicative Exchange Rate",
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFA1A1A1)
            )
            Text(
                text = if (exchangeRate != -1.0) {
                    "1 ${currencyFrom.value} = ${exchangeRate} ${currencyTo.value}"
                } else {
                    "Exchange rate not found"
                },
                fontSize = 18.sp,
                fontWeight = FontWeight(500),
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun ExchangeRateContentPreview() {
    CurrencyTheme {
        ExchangeRateContent(
            currencyFrom = mutableStateOf(Currency.getInstance("USD")),
            currencyTo = mutableStateOf(Currency.getInstance("KRW")),
            exchangeRate = 1.0
        )
    }
}
