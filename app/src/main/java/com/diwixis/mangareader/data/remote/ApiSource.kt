package com.diwixis.mangareader.data.remote

import com.diwixis.mangareader.data.remote.api.AuthApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

/**
 * Источник данных API.
 * Этот класс является агригатором всех API приложения.
 *
 * @author П. Густокашин (Diwixis)
 */
class ApiSource(client: HttpClientFactory, authDataFactory: AuthDataFactory) {

    private val onTokenSupplier: () -> String = { authDataFactory.token }

    private val onNewTokenSupplier: () -> Deferred<String> = {
        CoroutineScope(Dispatchers.Main).async {
            val token = authApi.refreshAuthToken(authDataFactory.refreshToken)
            authDataFactory.update(token = token)
        }
    }

    val authApi by lazy { AuthApi(client.create(AuthService::class)) }
}