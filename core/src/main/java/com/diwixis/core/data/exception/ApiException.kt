package com.diwixis.core.data.exception

import com.diwixis.core.domain.network.ErrorWrapper

/**
 * Исключение API запроса.
 *
 * @author П. Густокашин (Diwixis)
 */
open class ApiException(
    override val message: String? = null,
    override val cause: Throwable? = null,
    val errorWrapper: ErrorWrapper? = null
) : RuntimeException() {

    constructor(exception: ApiException) : this(
        message = exception.message,
        cause = exception.cause,
        errorWrapper = exception.errorWrapper
    )
}