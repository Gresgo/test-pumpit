package com.test.pumpit.providers

import com.test.pumpit.ui.issues.IssuesRepository

object GithubRepositoryProvider {
    fun provideRepository() : IssuesRepository {
        return IssuesRepository()
    }
}