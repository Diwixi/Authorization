package com.diwixis.mangareader.domain.usecase

import com.diwixis.mangareader.domain.model.network.AuthToken

/**
 * Use Case авторизации.
 *
 * @author П. Густокашин (Diwixis)
 */
interface AuthUseCase {

    /**
     * Авторизоваться.
     *
     * @param login Логин пользователя.
     * @param password Пароль пользователя.
     */
    suspend fun signIn(login: String, password: String): AuthToken

    /** Выйти из системы */
    suspend fun logOut()

    /** Авторизован ли пользователь */
    fun isLoggedIn(): Boolean
}