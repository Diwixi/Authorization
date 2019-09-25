package com.diwixis.mangareader.presentation.screens.authorization

import android.view.View
import com.diwixis.mangareader.utils.Action
import com.diwixis.mangareader.utils.extensions.visible
import kotlinx.android.synthetic.main.activity_authorization.view.*

/**
 * Холдер авторизации.
 *
 * @author П. Густокашин (Diwixis)
 */
class AuthorizationViewHolder(private val rootView: View) {

    val login: String
        get() = rootView.authLoginEditText.text.toString()

    val pass: String
        get() = rootView.authPassEditText.text.toString()

    var authIsProgressIndicatorVisible: Boolean
        get() = rootView.authProgress.visibility == View.VISIBLE
        set(value) {
            rootView.authProgress.visibility = if (value) View.VISIBLE else View.GONE
        }

    var onClickLogin: Action? = null

    fun showError(textError: String?) {
        with(rootView) {
            authErrorContainer.visible()
            authErrorTextView.text = textError
        }
    }

    fun setup(init: AuthorizationViewHolder.() -> Unit): AuthorizationViewHolder {
        init()
        with(rootView) {
            loginButton.setOnClickListener { onClickLogin?.invoke() }
        }
        return this
    }

    fun bind(login: String, password: String) {
        with(rootView) {
            authLoginEditText.setText(login)
            authPassEditText.setText(password)
        }
    }
}