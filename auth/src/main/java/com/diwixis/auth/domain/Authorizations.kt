package com.diwixis.mangareader.domain.model.network

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

/**
 * Сетевые модели для авторизации
 *
 * @author П. Густокашин (Diwixis)
 */

/**
 * Модели для работы с веб-сервисами.
 *
 * @param accessToken Сгенерированный токен доступа
 * @param tokenType Тип токена.
 * @param expiresIn Срок жизни токена в секундах с момента его генерации
 *
 * @author А. Афанасьев.
 */
@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class AuthToken(
    @JsonProperty(value = "access_token", required = true) val accessToken: String,
    @JsonProperty(value = "create_at") val createAt: Int,
    @JsonProperty(value = "expires_in", required = true) val expiresIn: Int,
    @JsonProperty(value = "refresh_token", required = true) val refreshToken: String,
    @JsonProperty(value = "scope", required = true) val scope: String,
    @JsonProperty(value = "token_type", required = true) val tokenType: String
) : Parcelable