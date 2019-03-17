package com.diwixis.mangareader.data.remote

import com.diwixis.mangareader.data.remote.api.AuthApi
import io.reactivex.Single

/**
 * Источник данных API.
 * Этот класс является агригатором всех API приложения.
 *
 * @author П. Густокашин (Diwixis)
 */
class ApiSource(client: HttpClientFactory, authDataFactory: AuthDataFactory) {

    private val onTokenSupplier: () -> String = { authDataFactory.token }

    private val onNewTokenSupplier: (() -> Single<String>) = {
        authApi.refreshAuthToken(authDataFactory.refreshToken)
            .map { authDataFactory.update(token = it) }
    }

    val authApi by lazy { AuthApi(client.create(AuthService::class)) }
}