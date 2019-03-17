package com.diwixis.mangareader.domain.repository

import io.reactivex.Completable

/**
 * Репозиторий авторизации.
 *
 * @author П. Густокашин (Diwixis)
 */
interface AuthRepository {

    /**
     * Получение токена авторизации.
     *
     * @param login Логин пользователя.
     * @param password Пароль пользователя.
     */
    fun getAuthToken(login: String, password: String): Completable
}