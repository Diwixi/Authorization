package com.diwixis.mangareader.di.modules

import android.content.Context
import com.diwixis.mangareader.data.local.Preferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Модуль кеша.
 *
 * @author П. Густокашин (Diwixis)
 */
@Module
@Suppress("UndocumentedPublicFunction")
class CacheModule {

    @Singleton
    @Provides
    fun providePreferences(context: Context): Preferences {
        return Preferences(context)
    }
}