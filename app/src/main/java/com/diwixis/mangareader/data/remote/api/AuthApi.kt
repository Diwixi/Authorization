package com.diwixis.mangareader.data.remote.api

import com.diwixis.mangareader.BuildConfig
import com.diwixis.mangareader.data.remote.AuthService
import com.diwixis.mangareader.domain.model.network.AuthToken

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
class AuthApi(private val service: AuthService) {
    suspend fun getAuthToken(
        userName: String,
        userPass: String
    ): AuthToken {
        return service.getAuthToken(
            userName,
            userPass,
            GRANT_TYPE_PASS,
            BuildConfig.CLIENT_ID,
            BuildConfig.CLIENT_SECRET
        )
    }

    suspend fun refreshAuthToken(
        refreshToken: String
    ): AuthToken {
        return service.refreshAuthToken(
            refreshToken,
            GRANT_TYPE_REFRESH,
            BuildConfig.CLIENT_ID,
            BuildConfig.CLIENT_SECRET
        )
    }

    companion object {
        private const val GRANT_TYPE_PASS = "password"
        private const val GRANT_TYPE_REFRESH = "refresh_token"
    }
}