package com.diwixis.mangareader.data.local

import android.content.Context
import com.diwixis.mangareader.utils.delegate
import com.diwixis.mangareader.utils.nullableString
import java.util.*

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
class Preferences(context: Context) {
    private val prefs by lazy { context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) }

    var userToken by prefs.nullableString(key = PREF_USER_TOKEN)
    var userRefreshToken by prefs.nullableString(key = PREF_USER_REFRESH_TOKEN)
    var userLogin by prefs.nullableString(key = PREF_USER_LOGIN)

    var firstApplicationLaunch by prefs.delegate<Date?>(
        defaultValue = null,
        key = PREF_FIRST_APPLICATION_LAUNCH_IN_MILLIS,
        getter = { key, defaultValue ->
            if (prefs.contains(key)) Date(getLong(key, 0)) else defaultValue
        },
        setter = { key, value ->
            value?.let { putLong(key, it.time) } ?: remove(key)
        })

    private companion object {
        private const val PREF_NAME = "mr_prefs"
        private const val PREF_USER_LOGIN = "mr_user_login"
        private const val PREF_USER_TOKEN = "mr_token"
        private const val PREF_USER_REFRESH_TOKEN = "mr_refresh_token"
        private const val PREF_FIRST_APPLICATION_LAUNCH_IN_MILLIS = "first_application_launch_in_millis"
    }
}