package com.test.pumpit.application

import com.test.pumpit.ui.issues.IssuesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeFragment(): IssuesFragment
}