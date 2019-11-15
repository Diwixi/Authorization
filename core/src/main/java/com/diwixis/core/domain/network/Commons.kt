package com.diwixis.core.domain.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Общие сетевые модели
 *
 * @author П. Густокашин (Diwixis)
 */

/**
 * Модель обертки ошибки.
 *
 * @property error Ошибка.
 */
@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class ErrorWrapper(
    @JsonProperty(value = "error", required = true) val error: Error
) : Parcelable

/**
 * Модель ошибки, полученной от сервера.
 *
 * @property code код ошибки.
 * @property text текст ошибки.
 */
@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class Error(
    @JsonProperty(value = "code", required = true) val code: String,
    @JsonProperty(value = "text", required = true) val text: String
) : Parcelable