package com.diwixis.mangareader.data.exception

import com.diwixis.mangareader.domain.model.network.Error
import com.diwixis.mangareader.domain.model.network.ErrorWrapper
import com.diwixis.mangareader.presentation.common.Response
import retrofit2.HttpException

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
fun Throwable.processApiException(): Response<Unit> {
    return when (this) {
        is ApiException -> Response.failure(error = this)
        is HttpException -> Response.failure(error = this.asApiException())
        else -> Response.failure(error = AuthExceptionMsg.UNKNOWN_ERROR.asAuthException())
    }
}

fun HttpException.asApiException() =
    ApiException(message, cause, ErrorWrapper(Error("${code()}", message ?: "")))