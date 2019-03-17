package com.diwixis.mangareader.di.modules

import com.diwixis.mangareader.presentation.screens.MainActivity
import com.diwixis.mangareader.presentation.screens.authorization.AuthorizationActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Модуль привязки activity.
 *
 * @author П. Густокашин (Diwixis)
 */
@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindAuthorizationActivity(): AuthorizationActivity
}