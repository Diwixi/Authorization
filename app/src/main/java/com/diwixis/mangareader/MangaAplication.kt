package com.diwixis.mangareader

import android.app.Activity
import android.app.Application
import com.diwixis.mangareader.di.ApplicationComponent
import com.diwixis.mangareader.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Класс приложения.
 *
 * @author П. Густокашин (Diwixis)
 */
class MangaAplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    private lateinit var applicationComponent: ApplicationComponent

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()

//        Timber.plant(if (IS_DEBUG) DebugLogTree() else ReleaseLogTree())

        applicationComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .apply { inject(this@MangaAplication) }
    }

    private companion object {
        private val IS_DEBUG = BuildConfig.DEBUG
    }
}