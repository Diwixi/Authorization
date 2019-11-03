package com.diwixis.mangareader.presentation.screens.authorization

import androidx.lifecycle.*
import com.diwixis.mangareader.data.exception.ApiException
import com.diwixis.mangareader.data.exception.AuthException
import com.diwixis.mangareader.data.exception.AuthExceptionMsg
import com.diwixis.mangareader.data.exception.processApiException
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

    val isLoggedIn: Boolean
        get() = authUseCase.isLoggedIn()

    fun login(login: String, pass: String): LiveData<Response<Unit>> = liveData {
        emit(Response.loading())

        AuthException.checkFields(login, pass)?.let {
            emit(it as Response<Unit>)
            return@liveData
        }

        try {
            withContext(Dispatchers.IO) { authUseCase.signIn(login = login, password = pass) }
            emit(Response.success(value = Unit))
        } catch (e: Throwable) {
            emit(e.processApiException())
        }
    }
}