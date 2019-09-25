package com.diwixis.mangareader.presentation.screens.authorization

import androidx.lifecycle.*
import com.diwixis.mangareader.data.exception.ApiException
import com.diwixis.mangareader.data.exception.AuthException
import com.diwixis.mangareader.data.exception.AuthExceptionMsg
import com.diwixis.mangareader.domain.usecase.AuthUseCase
import com.diwixis.mangareader.presentation.common.Response
import com.diwixis.mangareader.utils.extensions.loginIsValid
import com.diwixis.mangareader.utils.extensions.passwordIsValid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent

/**
 * Модель авторизации
 *
 * @author П. Густокашин (Diwixis)
 */
@ExperimentalCoroutinesApi
class AuthorizationViewModel constructor(
    private val authUseCase: AuthUseCase
) : ViewModel(), KoinComponent {
    private val _authLiveData = MutableLiveData<Response<Unit>>()
    val authLiveData: LiveData<Response<Unit>>
        get() = _authLiveData

    val isLoggedIn: Boolean
        get() = authUseCase.isLoggedIn()

    private fun isValidFields(login: String, pass: String): Boolean {
        return validation(login, pass)?.let {
            _authLiveData.value = Response.failure(it)
            false
        } ?: true
    }

    fun login(login: String, pass: String) {
        if (!isValidFields(login, pass)) return

        viewModelScope.launch {
            _authLiveData.value = Response.loading()
            _authLiveData.value = try {
                withContext(Dispatchers.IO) { authUseCase.signIn(login = login, password = pass) }
                Response.success(value = Unit)
            } catch (e: ApiException) {
                Response.failure(error = e)
            } catch (e: IllegalArgumentException) {
                Response.failure(error = AuthException(ApiException(AuthExceptionMsg.UNKNOWN_ERROR.msg)))
            }
        }
    }

    private fun validation(login: String, pass: String): AuthException? {
        when {
            (login.isEmpty() && pass.isEmpty()) -> {
                return AuthException(ApiException(AuthExceptionMsg.ALL_IS_EMPTY.msg))
            }
            login.isEmpty() -> {
                return AuthException(ApiException(AuthExceptionMsg.LOGIN_IS_EMPTY.msg))
            }
            pass.isEmpty() -> {
                return AuthException(ApiException(AuthExceptionMsg.PASS_IS_EMPTY.msg))
            }
            !login.loginIsValid() -> {
                return AuthException(ApiException(AuthExceptionMsg.LOGIN_IS_NOT_VALID.msg))
            }
            !pass.passwordIsValid() -> {
                return AuthException(ApiException(AuthExceptionMsg.PASS_IS_NOT_VALID.msg))
            }
            else -> return null
        }
    }
}
