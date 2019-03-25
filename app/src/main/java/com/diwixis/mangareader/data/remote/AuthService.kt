package com.diwixis.mangareader.data.remote

import com.diwixis.mangareader.domain.model.network.AuthToken
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

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
    suspend fun getAuthToken(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): AuthToken

    @POST("oauth/token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun refreshAuthToken(
        @Field("refresh_token") refreshToken: String,
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): AuthToken
}