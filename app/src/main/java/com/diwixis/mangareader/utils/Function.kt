package com.diwixis.mangareader.utils

import com.diwixis.mangareader.presentation.common.Response
import kotlinx.coroutines.CoroutineScope

/**
 * Группа Функциональных интерфейсов (коллбеки)
 *
 * @author П. Густокашин (Diwixis)
 */
/** возвращает значение. */
typealias Supplier<T> = () -> T

/** возвращает `true` или `false` для заданного входного значения. */
typealias Predicate<T> = (T) -> Boolean

/** принимает одно значение. */
typealias Consumer<T> = (T) -> Unit

/** действие без значения на вход и выход. */
typealias Action = () -> Unit

/** задача без значений на вход и выход. */
typealias Task = suspend CoroutineScope.() -> Unit

/** задача на обработку ошибки. */
typealias TaskThrowable = suspend CoroutineScope.(Throwable) -> Unit

/** задача с возвращением значения. */
typealias TaskGeneric<T> = suspend CoroutineScope.() -> T

/** задача с возвращением результата со значением. */
typealias TaskResult<T> = suspend CoroutineScope.() -> Response<T>