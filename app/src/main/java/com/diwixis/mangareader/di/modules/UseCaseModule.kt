package com.diwixis.mangareader.di.modules

import com.diwixis.mangareader.data.remote.AuthDataFactory
import com.diwixis.mangareader.domain.repository.AuthRepository
import com.diwixis.mangareader.domain.usecase.AuthUseCase
import com.diwixis.mangareader.domain.usecase.AuthUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
@Module
class UseCaseModule {
    @Singleton
    @Provides
    fun provideAuthUseCase(auth: AuthRepository, authDataFactory: AuthDataFactory): AuthUseCase {
        return AuthUseCaseImpl(auth, authDataFactory)
    }
}