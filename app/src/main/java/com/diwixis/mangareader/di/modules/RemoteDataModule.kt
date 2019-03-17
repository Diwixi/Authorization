package com.diwixis.mangareader.di.modules

import android.content.Context
import com.diwixis.mangareader.BuildConfig
import com.diwixis.mangareader.data.local.Preferences
import com.diwixis.mangareader.data.remote.ApiSource
import com.diwixis.mangareader.data.remote.AuthDataFactory
import com.diwixis.mangareader.data.remote.HttpClient
import com.diwixis.mangareader.data.remote.HttpClientFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Модуль удалённых данных
 *
 * @author П. Густокашин (Diwixis)
 */
@Module
class RemoteDataModule {

    private val apiBaseUrl: String = BuildConfig.API_BASE_URL
    private val isDebugging: Boolean = BuildConfig.DEBUG

    @Provides
    fun provideHttpClientFactory(context: Context): HttpClientFactory {
        val original = HttpClient(context, apiBaseUrl, isDebugging)
        return HttpClientFactory(original)
    }

    @Singleton
    @Provides
    fun provideAuthDataFactory(preferences: Preferences): AuthDataFactory {
        return AuthDataFactory(preferences)
    }

    @Singleton
    @Provides
    fun provideApiSource(client: HttpClientFactory, authDataFactory: AuthDataFactory): ApiSource {
        return ApiSource(client, authDataFactory)
    }
}