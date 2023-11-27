package com.nighttwo1.presentation.ui

import java.text.DecimalFormat

fun toDecimalFormat(num: Double, pattern: String = "#,##0.00"): String {
    val decimal = DecimalFormat(pattern)
    return decimal.format(num)
}