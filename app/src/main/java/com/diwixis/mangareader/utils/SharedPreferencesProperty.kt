package com.diwixis.mangareader.utils

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Набор для работы с [SharedPreferences]
 *
 * @author П. Густокашин (Diwixis)
 */

/**
 * Property delegate для [Boolean] значений, с поддержкой `null`.
 *
 * @param defaultValue Значение по умолчанию.
 * @param key Ключ значения, если ключ не задан будет подставлено имя параметра.
 *
 * @see delegate
 */
fun SharedPreferences.nullableString(
    defaultValue: String? = null,
    key: String? = null
): ReadWriteProperty<Any, String?> {
    return delegate(defaultValue, key, SharedPreferences::getString, SharedPreferences.Editor::putString)
}

/**
 * Property delegate взаимодействия с [SharedPreferences].
 *
 * @param defaultValue Значение по умолчанию.
 * @param key Ключ значения, если ключ не задан будет подставлено имя параметра.
 */
inline fun <T> SharedPreferences.delegate(
    defaultValue: T,
    key: String?,
    crossinline getter: SharedPreferences.(String, T) -> T,
    crossinline setter: SharedPreferences.Editor.(String, T) -> SharedPreferences.Editor
): ReadWriteProperty<Any, T> {
    return object : ReadWriteProperty<Any, T> {
        override fun getValue(thisRef: Any, property: KProperty<*>): T {
            return getter(key ?: property.name, defaultValue)
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
            edit().setter(key ?: property.name, value).apply()
        }
    }
}