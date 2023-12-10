package com.nighttwo1.presentation

import androidx.compose.ui.graphics.vector.ImageVector
import com.nighttwo1.presentation.currencyicons.IcExchange
import kotlin.collections.List as ____KtList

public object CurrencyIcons

private var __AllIcons: ____KtList<ImageVector>? = null

public val CurrencyIcons.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(IcExchange)
    return __AllIcons!!
  }
