package com.diwixis.mangareader.domain.usecase

import com.diwixis.mangareader.data.remote.AuthDataFactory
import com.diwixis.mangareader.domain.model.network.AuthToken
import com.diwixis.mangareader.domain.repository.AuthRepository

/**
 * Реализация [AuthUseCase]
 *
 * @author П. Густокашин (Diwixis)
 */
class AuthUseCaseImpl(
    private val auth: AuthRepository,
    private val authDataFactory: AuthDataFactory
) : AuthUseCase {

    override suspend fun signIn(login: String, password: String): AuthToken {
        return auth.getAuthToken(login, password)
    }

    override suspend fun logOut() {
        authDataFactory.clear()
    }

    override fun isLoggedIn(): Boolean {
        return authDataFactory.run { login.isNotBlank() && token.isNotBlank() && refreshToken.isNotBlank()}
    }
}