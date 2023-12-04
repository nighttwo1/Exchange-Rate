package com.nighttwo1.currency.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nighttwo1.currency.BuildConfig
import com.nighttwo1.data.service.CurrencyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.*
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Singleton

/**
 * [API 정보](https://exchangeratesapi.io/documentation/)
 */
@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    fun provideBaseUrl() = BuildConfig.API_BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(RequestInterceptor())
            .build()
    } else OkHttpClient
        .Builder()
        .addInterceptor(RequestInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: String,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideCurrencyService(retrofit: Retrofit): CurrencyService = retrofit.create(CurrencyService::class.java)
}

class RequestInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val newRequest = request().url.newBuilder()
            .addQueryParameter("access_key", BuildConfig.API_KEY)
            .build()
        proceed(chain.request().newBuilder().url(newRequest).build())
    }
}
