package com.diwixis.mangareader.data.exception

import com.diwixis.mangareader.domain.model.network.AuthToken
import com.diwixis.mangareader.domain.model.network.ErrorWrapper
import com.diwixis.mangareader.presentation.common.Response
import com.diwixis.mangareader.utils.extensions.loginIsValid
import com.diwixis.mangareader.utils.extensions.passwordIsValid

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

class AuthException(exception: ApiException) : ApiException(exception) {
    companion object {
        fun checkFields(login: String, pass: String): Response<AuthException>? {
            when {
                (login.isEmpty() && pass.isEmpty()) -> {
                    return Response.failure(error = AuthException(ApiException(AuthExceptionMsg.ALL_IS_EMPTY.msg)))
                }

                login.isEmpty() -> {
                    return Response.failure(error = AuthException(ApiException(AuthExceptionMsg.LOGIN_IS_EMPTY.msg)))
                }

                pass.isEmpty() -> {
                    return Response.failure(error = AuthException(ApiException(AuthExceptionMsg.PASS_IS_EMPTY.msg)))
                }

                !login.loginIsValid() -> {
                    return Response.failure(error = AuthException(ApiException(AuthExceptionMsg.LOGIN_IS_NOT_VALID.msg)))
                }

                !pass.passwordIsValid() -> {
                    return Response.failure(error = AuthException(ApiException(AuthExceptionMsg.PASS_IS_NOT_VALID.msg)))
                }

                else -> return null
            }
        }
    }
}

enum class AuthExceptionMsg(val msg: String) {
    ALL_IS_EMPTY("All fields is empty"),
    LOGIN_IS_EMPTY("Login is empty"),
    PASS_IS_EMPTY("Pass is empty"),
    LOGIN_IS_NOT_VALID("Login is not valid"),
    PASS_IS_NOT_VALID("Pass is not valid"),
    UNKNOWN_ERROR("Unknown error"),
    OK("OK");

    companion object {
        fun fromString(msg: String?): AuthExceptionMsg? {
            for (value in values()) {
                if (value.msg == msg) {
                    return value
                }
            }
            return null
        }
    }
}

fun AuthExceptionMsg.asAuthException() = AuthException(ApiException(msg))

/**
 * Исключение невалидного токена.
 *
 * @author П. Аннин.
 */
class InvalidTokenException(exception: ApiException) : ApiException(exception)

fun ApiException.wrapClarify(): Throwable {
    return when (this.errorWrapper?.error?.code) {
        "invalid_token" -> InvalidTokenException(this)
        "auth" -> ApiException(this)
        else -> this
    }
}