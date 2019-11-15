package com.diwixis.mangareader.data.repository

import com.diwixis.core.remote.ApiSource
import com.diwixis.mangareader.data.remote.AuthDataFactory
import com.diwixis.mangareader.domain.model.network.AuthToken
import com.diwixis.auth.repository.AuthRepository

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
class AuthRepositoryImpl(
    private val api: com.diwixis.core.remote.ApiSource,
    private val authDataFactory: AuthDataFactory
) : com.diwixis.auth.repository.AuthRepository {
    override suspend fun getAuthToken(login: String, password: String) {
        api.authApi.getAuthToken(login, password).apply { authDataFactory.update(login, this) }
    }
}