package com.test.pumpit.application

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component (modules = [AndroidInjectionModule::class, ActivityModule::class, FragmentModule::class])
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {

        fun build() : AppComponent

        @BindsInstance
        fun appBind(app: App): Builder

    }

}