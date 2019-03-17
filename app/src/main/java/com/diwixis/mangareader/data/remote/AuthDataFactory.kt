package com.diwixis.mangareader.data.remote

import com.diwixis.mangareader.data.local.Preferences
import com.diwixis.mangareader.domain.model.network.AuthToken

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
class AuthDataFactory(
    @set:Synchronized private var userLogin: String = "",
    @set:Synchronized private var _token: String = "",
    @set:Synchronized private var _refreshToken: String = "",
    private val onTokenUpdated: (newToken: String, newRefreshToken: String) -> Unit,
    private val onLoginUpdated: (newLogin: String) -> Unit
) {

    constructor(preferences: Preferences) : this(
        userLogin = preferences.userLogin ?: "",
        _token = preferences.userToken ?: "",
        _refreshToken = preferences.userRefreshToken ?: "",
        onTokenUpdated = { newToken, newRefreshToken ->
            preferences.userToken = newToken
            preferences.userRefreshToken = newRefreshToken
        },
        onLoginUpdated = { newLogin ->
            preferences.userLogin = newLogin
        }
    )

    val login: String
        get() = userLogin

    val token: String
        get() = _token

    val refreshToken: String
        get() = _refreshToken

    fun update(login: String, token: AuthToken) {
        userLogin = login
        onLoginUpdated(userLogin)
        update(token)
    }

    fun update(token: AuthToken): String {
        _token = "${token.tokenType} ${token.accessToken}"
        _refreshToken = token.refreshToken
        onTokenUpdated(_token, _refreshToken)
        return _token
    }

    fun clear() {
        userLogin = ""
        _token = ""
        _refreshToken = ""

        onTokenUpdated(_token, _refreshToken)
    }
}