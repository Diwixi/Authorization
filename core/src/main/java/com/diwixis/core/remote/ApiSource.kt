package com.diwixis.core.remote

import com.diwixis.mangareader.data.remote.api.AuthApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

/**
 * Источник данных API.
 * Этот класс является агригатором всех API приложения.
 *
 * @author П. Густокашин (Diwixis)
 */
class ApiSource(client: HttpClientFactory, authDataFactory: AuthDataFactory) : KoinComponent {

    private val onTokenSupplier: () -> String = { authDataFactory.token }
    val authApi: AuthApi by inject { parametersOf(client.create(AuthService::class)) }

    private val onNewTokenSupplier: () -> Deferred<String> = {
        takeIf {  }
        CoroutineScope(Dispatchers.Main).async {
            val token = authApi.refreshAuthToken(authDataFactory.refreshToken)
            authDataFactory.update(token = token)
        }
    }
}