package com.diwixis.mangareader.domain.usecase

import io.reactivex.Completable

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
    fun signIn(login: String, password: String): Completable

    /** Выйти из системы */
    fun logOut()

    /** Авторизован ли пользователь */
    fun isLoggedIn(): Boolean
}