package com.diwixis.mangareader

import com.diwixis.mangareader.data.local.Preferences
import com.diwixis.mangareader.data.remote.ApiSource
import com.diwixis.mangareader.data.remote.AuthDataFactory
import com.diwixis.mangareader.data.remote.HttpClient
import com.diwixis.mangareader.data.remote.HttpClientFactory
import com.diwixis.mangareader.data.repository.AuthRepositoryImpl
import com.diwixis.mangareader.domain.repository.AuthRepository
import com.diwixis.mangareader.domain.usecase.AuthUseCase
import com.diwixis.mangareader.domain.usecase.AuthUseCaseImpl
import com.diwixis.mangareader.presentation.screens.authorization.AuthorizationViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
val modules = module {
    single { Dispatchers.IO }
    single { Preferences(get()) }
}

val authModule = module {
    single<AuthUseCase> { AuthUseCaseImpl(get(), get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single { AuthDataFactory(get()) }
    single { ApiSource(get(), get()) }
    single { HttpClientFactory(get()) }
    single { HttpClient(get(), "", false) }
}

val viewModels = module {
    viewModel { AuthorizationViewModel(get()) }
}