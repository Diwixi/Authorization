package com.diwixis.mangareader.utils.extensions

/**
 * Набор extension для типа String
 *
 * @author П. Густокашин (Diwixis)
 */
fun String.loginIsValid(): Boolean {
    return this.isNotEmpty() && this.matches("[A-Za-z0-9@.]{4,20}".toRegex())
}

fun String.passwordIsValid(): Boolean {
    return this.isNotEmpty() && this.matches("[A-Za-z0-9\\W'_]{8,30}".toRegex())
}
