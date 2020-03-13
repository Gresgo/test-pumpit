package com.test.pumpit.application

import android.app.Application
import android.content.Context

class App : Application() {

    /**
     * context singleton
     */
    private companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    /**
     * able to get appContext from everywhere
     */
    fun getContext() = context

}