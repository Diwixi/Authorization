package com.diwixis.mangareader.di

import com.diwixis.mangareader.MangaAplication
import com.diwixis.mangareader.di.modules.ActivityBindingModule
import com.diwixis.mangareader.di.modules.AppModule
import com.diwixis.mangareader.di.modules.ViewModelBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author П. Густокашин (Diwixis)
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        ViewModelBindingModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<MangaAplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MangaAplication): Builder

        fun build(): ApplicationComponent
    }
}