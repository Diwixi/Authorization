package com.diwixis.mangareader.presentation.screens.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diwixis.mangareader.data.exception.ApiException
import com.diwixis.mangareader.data.exception.AuthException
import com.diwixis.mangareader.data.exception.AuthExceptionMsg
import com.diwixis.mangareader.domain.usecase.AuthUseCase
import com.diwixis.mangareader.presentation.common.Response
import com.diwixis.mangareader.utils.SchedulerFacade
import com.diwixis.mangareader.utils.extensions.loginIsValid
import com.diwixis.mangareader.utils.extensions.passwordIsValid
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

/**
 * Модель авторизации
 *
 * @author П. Густокашин (Diwixis)
 */
class AuthorizationViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val scheduler: SchedulerFacade
) : ViewModel() {
    private val _authLiveData = MutableLiveData<Response<Unit>>()
    private val rxDisposables = CompositeDisposable()

    val authLiveData: LiveData<Response<Unit>>
        get() = _authLiveData

    val isLoggedIn: Boolean
        get() = authUseCase.isLoggedIn()

    override fun onCleared() {
        super.onCleared()
        rxDisposables.dispose()
    }

    private fun checkFields(login: String, pass: String): Boolean {
        return validation(login, pass)?.let {
            _authLiveData.value = Response.failure(it)
            false
        } ?: true
    }

    fun login(login: String, pass: String) {
        if (checkFields(login, pass)) {
            authUseCase.signIn(login = login, password = pass)
                .subscribeOn(scheduler.io)
                .observeOn(scheduler.ui)
                .doOnSubscribe { _authLiveData.value = Response.loading() }
                .subscribe(
                    { _authLiveData.value = Response.success(value = Unit) },
                    { _authLiveData.value = Response.failure(error = it) }
                ).addTo(rxDisposables)
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
