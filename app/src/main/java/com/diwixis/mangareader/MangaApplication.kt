package com.diwixis.mangareader

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Класс приложения.
 *
 * @author П. Густокашин (Diwixis)
 */
class MangaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MangaApplication)
            modules(listOf(modules, viewModels, authModule, networkModule))
        }
    }

    private companion object {
        private val IS_DEBUG = BuildConfig.DEBUG
    }
}