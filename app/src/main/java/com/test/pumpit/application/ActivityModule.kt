package com.test.pumpit.application

import com.test.pumpit.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector//(modules = [MainActivityModule::class])
    abstract fun contributeActivityAndroidInjector() : MainActivity

}