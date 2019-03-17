package com.diwixis.mangareader.di.modules

import android.content.Context
import com.diwixis.mangareader.MangaAplication
import com.diwixis.mangareader.utils.SchedulerFacade
import dagger.Module
import dagger.Provides


/**
 * @author П. Густокашин (Diwixis)
 */

@Module(
    includes = [
        RemoteDataModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        CacheModule::class
    ]
)
class AppModule {
    @Provides
    fun provideContext(app: MangaAplication): Context {
        return app.applicationContext
    }

    @Provides
    fun provideSchedulerFacade(): SchedulerFacade {
        return SchedulerFacade()
    }
}