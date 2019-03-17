package com.diwixis.mangareader.utils.extensions

import android.os.Bundle

/**
 * Набор Extension функций для Bundle
 *
 * @author П. Густокашин (Diwixis)
 */

/** Проверяет значение по ключу в [Bundle] и если тот отсутствует - вызовет исключение */
@Suppress("UNCHECKED_CAST")
fun <T> Bundle.require(key: String): T {
    check(value = containsKey(key), lazyMessage = { "Value with key $key not found." })
    return get(key) as T
}

/** Делает то же, что и [Bundle.require], но с возможностью выбора альтернативного [Bundle] */
fun <T> Bundle?.require(key: String, alternative: Bundle? = null): T {
    return when {
        this?.containsKey(key) ?: false -> requireNotNull(value = this).require(key)
        alternative?.containsKey(key) ?: false -> requireNotNull(value = alternative).require(key)
        else -> throw IllegalArgumentException("Value with key $key not found.")
    }
}

/**
 * [Bundle] в виде DSL.
 */
inline fun bundle(body: Bundle.() -> Unit): Bundle {
    return Bundle().apply(body)
}