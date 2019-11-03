package com.diwixis.mangareader.presentation.screens.authorization

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.diwixis.mangareader.R
import com.diwixis.mangareader.data.exception.ApiException
import com.diwixis.mangareader.data.exception.AuthException
import com.diwixis.mangareader.presentation.common.Failure
import com.diwixis.mangareader.presentation.common.Progress
import com.diwixis.mangareader.presentation.common.Response
import com.diwixis.mangareader.presentation.common.Success
import com.diwixis.mangareader.presentation.screens.MainActivity
import kotlinx.android.synthetic.main.activity_authorization.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.BuildConfig
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Экран авторизации.
 *
 * @author П. Густокашин (Diwixis)
 */
@ExperimentalCoroutinesApi
class AuthorizationActivity : AppCompatActivity() {

    private val viewModel by viewModel<AuthorizationViewModel>()
    private lateinit var viewHolder: AuthorizationViewHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        with(viewModel) {
            if (isLoggedIn) openMain("Is Logged In")
        }

        viewHolder = AuthorizationViewHolder(authRootLayout).setup {
            onClickLogin = {
                viewModel.login(login, pass).observe(this@AuthorizationActivity, authObserver)
            }
        }
    }

    private val authObserver = Observer<Response<Unit>> { response ->
        when (response) {
            is Progress -> viewHolder.authIsProgressIndicatorVisible = true
            is Success -> openMain(response.value.toString())
            is Failure -> with(viewHolder) {
                viewHolder.authIsProgressIndicatorVisible = false
                when (response.error) {
                    is AuthException -> showError(response.error.message)
                    is ApiException -> showError(response.error.errorWrapper?.error?.text)
                    else -> showError(response.error.message)
                }
            }
        }
    }

    private fun openMain(response: String) {
        MainActivity.launch(this, response)
        finish()
    }
}