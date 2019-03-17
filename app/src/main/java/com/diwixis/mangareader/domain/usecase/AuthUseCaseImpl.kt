package com.diwixis.mangareader.domain.usecase

import com.diwixis.mangareader.data.remote.AuthDataFactory
import com.diwixis.mangareader.domain.repository.AuthRepository
import io.reactivex.Completable

/**
 * Реализация [AuthUseCase]
 *
 * @author П. Густокашин (Diwixis)
 */
class AuthUseCaseImpl(
    private val auth: AuthRepository,
    private val authDataFactory: AuthDataFactory
) : AuthUseCase {

    override fun signIn(login: String, password: String): Completable {
        return auth.getAuthToken(login, password)
    }

    override fun logOut() {
        authDataFactory.clear()
    }

    override fun isLoggedIn(): Boolean {
        return authDataFactory.run { login.isNotBlank() && token.isNotBlank() && refreshToken.isNotBlank()}
    }
}