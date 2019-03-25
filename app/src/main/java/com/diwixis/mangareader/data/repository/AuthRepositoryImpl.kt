package com.diwixis.mangareader.data.repository

import com.diwixis.mangareader.data.remote.ApiSource
import com.diwixis.mangareader.data.remote.AuthDataFactory
import com.diwixis.mangareader.domain.model.network.AuthToken
import com.diwixis.mangareader.domain.repository.AuthRepository

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
class AuthRepositoryImpl(
    private val api: ApiSource,
    private val authDataFactory: AuthDataFactory
) : AuthRepository {
    override suspend fun getAuthToken(login: String, password: String): AuthToken {
        return api.authApi.getAuthToken(login, password).apply { authDataFactory.update(login, this) }
    }
}