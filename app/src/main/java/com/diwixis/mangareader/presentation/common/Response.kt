package com.diwixis.mangareader.presentation.common

/**
 * Обёртка результата с состоянием
 *
 * @author П. Густокашин (Diwixis)
 */
sealed class Response<out T> {
    companion object {
        /** Успешный запрос. */
        fun <T> success(value: T) = Success(value)

        /** Ошибка. */
        fun <T> failure(error: Throwable) = Failure<T>(error)

        /** В процессе выполнения. */
        fun <T> loading() = Progress<T>()
    }
}

data class Success<out T>(val value: T) : Response<T>()
data class Failure<out T>(val error: Throwable) : Response<T>()
class Progress<out T> : Response<T>()