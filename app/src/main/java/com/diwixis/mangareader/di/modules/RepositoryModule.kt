package com.diwixis.mangareader.di.modules

import com.diwixis.mangareader.data.remote.ApiSource
import com.diwixis.mangareader.data.remote.AuthDataFactory
import com.diwixis.mangareader.data.repository.AuthRepositoryImpl
import com.diwixis.mangareader.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides

/**
 * Модуль репозиториев
 *
 * @author П. Густокашин (Diwixis)
 */
@Module
class RepositoryModule {

    @Provides
    fun provideAuthRepository(api: ApiSource, authDataFactory: AuthDataFactory): AuthRepository {
        return AuthRepositoryImpl(api, authDataFactory)
    }
}