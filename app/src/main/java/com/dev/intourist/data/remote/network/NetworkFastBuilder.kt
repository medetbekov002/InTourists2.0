package com.dev.intourist.data.remote.network

//import com.alish.boilerplate.data.BuildConfig
//import com.alish.boilerplate.data.core.utils.jsonClient
import com.dev.intourist.data.BuildConfig
import com.dev.intourist.data.utils.jsonClient
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
//import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(com.dev.intourist.data.BuildConfig.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(
        jsonClient.asConverterFactory("application/json; charset=UTF8".toMediaType())
    )
    .build()

internal fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient()
    .newBuilder()
    .addInterceptor(provideLoggingInterceptor())
    .callTimeout(30, TimeUnit.SECONDS)
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)

private fun provideLoggingInterceptor() = HttpLoggingInterceptor().setLevel(
    if (com.dev.intourist.data.BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
)