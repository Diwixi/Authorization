package com.diwixis.mangareader.data.remote.api

import com.diwixis.mangareader.BuildConfig
import com.diwixis.mangareader.data.remote.AuthService
import com.diwixis.mangareader.domain.model.network.AuthToken
import io.reactivex.Single

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
class AuthApi(private val service: AuthService) {
    fun getAuthToken(
        userName: String,
        userPass: String
    ): Single<AuthToken> {
        return service.getAuthToken(
            userName,
            userPass,
            GRANT_TYPE_PASS,
            BuildConfig.CLIENT_ID,
            BuildConfig.CLIENT_SECRET
        )
    }

    fun refreshAuthToken(
        refreshToken: String
    ): Single<AuthToken> {
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