package com.diwixis.mangareader.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diwixis.mangareader.di.common.DaggerViewModelFactory
import com.diwixis.mangareader.di.common.ViewModelKey
import com.diwixis.mangareader.presentation.screens.authorization.AuthorizationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Модуль привязки ViewModel
 *
 * @author П. Густокашин (Diwixis)
 */
@Module
abstract class ViewModelBindingModule {

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

//    @Binds
//    @IntoMap
//    @ViewModelKey(value = MainViewModel::class)
//    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(value = AuthorizationViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthorizationViewModel): ViewModel
}