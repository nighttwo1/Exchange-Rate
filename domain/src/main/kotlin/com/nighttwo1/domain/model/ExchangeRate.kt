package com.nighttwo1.domain.model

import java.util.*

data class ExchangeRate(
    val from: Currency,
    val to: Currency,
    val rate: Double
)
