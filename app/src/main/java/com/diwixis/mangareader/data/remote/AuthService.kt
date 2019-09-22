package com.diwixis.mangareader.data.remote

import com.diwixis.mangareader.domain.model.network.AuthToken
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
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("oauth/token")
    suspend fun getAuthToken(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): AuthToken

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("oauth/token")
    suspend fun refreshAuthToken(
        @Field("refresh_token") refreshToken: String,
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): AuthToken
}