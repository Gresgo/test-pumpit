package com.test.pumpit.providers

import com.test.pumpit.ui.youdrive.YoudriveRepository

object YoudriveRepositoryProvider {
    fun provideRepository() : YoudriveRepository {
        return YoudriveRepository()
    }
}