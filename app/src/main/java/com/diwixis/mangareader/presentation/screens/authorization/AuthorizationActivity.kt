package com.diwixis.mangareader.presentation.screens.authorization

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.diwixis.mangareader.BuildConfig
import com.diwixis.mangareader.R
import com.diwixis.mangareader.data.exception.ApiException
import com.diwixis.mangareader.presentation.common.Failure
import com.diwixis.mangareader.presentation.common.Response
import com.diwixis.mangareader.presentation.common.Success
import com.diwixis.mangareader.presentation.screens.MainActivity
import kotlinx.android.synthetic.main.activity_authorization.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Экран авторизации.
 *
 * @author П. Густокашин (Diwixis)
 */
class AuthorizationActivity : AppCompatActivity() {

    private val viewModel by viewModel<AuthorizationViewModel>()
    private lateinit var viewHolder: AuthorizationViewHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        with(viewModel) {
            observeAuthorization()
            if (isLoggedIn) openMain("Is Logged In")
        }

        viewHolder = AuthorizationViewHolder(authRootLayout).setup {
            onClickLogin = { viewModel.login(login, pass) }

            if (BuildConfig.DEBUG) {
                bind(login = BuildConfig.DEV_USER_LOGIN, password = BuildConfig.DEV_USER_PASSWORD)
            }
        }
    }

    private fun AuthorizationViewModel.observeAuthorization() {
        val observer = Observer<Response<Unit>> { response ->
            when (response) {
                is Success -> openMain(response.value.toString())
                is Failure -> with(viewHolder) {
                    authIsProgressIndicatorVisible = false
                    when (response.error) {
                        is ApiException -> {
                            showError(response.error.errorWrapper?.error?.text)
                        }
                        else -> {
                            showError(response.error.message)
                        }
                    }
                }
            }
        }
        authLiveData.observe(this@AuthorizationActivity, observer)
    }

    private fun openMain(response: String) {
        MainActivity.launch(this, response)
        finish()
    }
}