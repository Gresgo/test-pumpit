package com.test.pumpit.application

import android.app.Application
import android.content.Context

class App : Application() {

    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    fun getContext() = context

}