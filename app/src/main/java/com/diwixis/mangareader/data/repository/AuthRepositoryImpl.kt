package com.diwixis.mangareader.data.repository

import com.diwixis.mangareader.data.remote.ApiSource
import com.diwixis.mangareader.data.remote.AuthDataFactory
import com.diwixis.mangareader.domain.repository.AuthRepository
import io.reactivex.Completable

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
class AuthRepositoryImpl(
    private val api: ApiSource,
    private val authDataFactory: AuthDataFactory
) : AuthRepository {
    override fun getAuthToken(login: String, password: String): Completable {
        return api.authApi.getAuthToken(login, password)
            .doOnSuccess { authDataFactory.update(login, it) }
            .ignoreElement()
    }
}