package com.test.pumpit.application

import android.app.Activity
import android.app.Application
import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    /**
     * context singleton
     */
    private companion object {
        lateinit var context: Context
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        DaggerAppComponent.builder()
            .appBind(this)
            .build()
            .inject(this)

    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    /**
     * able to get appContext from everywhere
     */
    fun getContext() = context

}