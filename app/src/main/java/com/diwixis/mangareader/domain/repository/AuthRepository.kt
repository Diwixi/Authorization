package com.diwixis.mangareader.domain.repository

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
    suspend fun getAuthToken(login: String, password: String)
}