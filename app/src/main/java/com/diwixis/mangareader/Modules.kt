package com.diwixis.mangareader

import com.diwixis.mangareader.data.local.Preferences
import com.diwixis.mangareader.data.remote.*
import com.diwixis.mangareader.data.remote.api.AuthApi
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
    factory { (service: AuthService) -> AuthApi(service) }
}

val networkModule = module {
    single { ApiSource(get(), get()) }
    single { HttpClientFactory(get()) }
    single { HttpClient(get(), BuildConfig.API_BASE_URL, BuildConfig.DEBUG) }
}

val viewModels = module {
    viewModel { AuthorizationViewModel(get()) }
}