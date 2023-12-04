package com.nighttwo1.data.service

import com.nighttwo1.data.models.CurrencyRate
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {
    @GET("/latest")
    suspend fun getLatestCurrencyRate(
//        Unsupported in Free Plan
//        @Query("base") from: String? = null,
        @Query("symbols") to: String? = null
    ): CurrencyRate
}