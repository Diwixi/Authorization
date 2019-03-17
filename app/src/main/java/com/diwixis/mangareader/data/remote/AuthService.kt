package com.diwixis.mangareader.data.remote

import com.diwixis.mangareader.domain.model.network.AuthToken
import io.reactivex.Single
import retrofit2.http.*

/**
 * Интерфейс для получения авторизац
 *
 * @author П. Густокашин (Diwixis)
 */
interface AuthService {

    /**
     * Получить токен доступа.
     */
    @POST("oauth/token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    fun getAuthToken(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): Single<AuthToken>

    @POST("oauth/token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    fun refreshAuthToken(
        @Field("refresh_token") refreshToken: String,
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): Single<AuthToken>
}