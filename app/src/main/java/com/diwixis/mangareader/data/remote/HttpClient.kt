package com.diwixis.mangareader.data.remote

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Клиент запросов
 *
 * @param context контекст
 * @param baseUrl базовый URL
 * @param isDebugging флаг, определяющий режим debug
 *
 * @author П. Густокашин (Diwixis)
 */
class HttpClient(
    private val context: Context,
    baseUrl: String,
    private val isDebugging: Boolean = false
) {
    val retrofit by lazy { configRetrofit(baseUrl) }

    private fun configHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context))
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_READ_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_WRITE_SECONDS, TimeUnit.SECONDS)

//        context?.let { okHttpClientBuilder.addInterceptor(configChuckInterceptor(context = it)) }
//        if (!isCertificateVerificationEnabled) {
//            trustAllCertificates(okHttpClientBuilder)
//        }

        return okHttpClientBuilder.build()
    }

    private fun configRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper()))
            .client(configHttpClient())
            .build()
    }

//    private fun configChuckInterceptor(context: Context): Interceptor {
//        return ChuckInterceptor(context)
//    }

    companion object {
        private const val TIMEOUT_SECONDS = 15L
        private const val TIMEOUT_READ_SECONDS = 15L
        private const val TIMEOUT_WRITE_SECONDS = 30L
    }
}