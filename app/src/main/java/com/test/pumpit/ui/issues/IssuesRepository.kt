package com.test.pumpit.ui.issues

import com.test.pumpit.models.ExtendedIssueModel
import com.test.pumpit.models.IssueModel
import io.reactivex.Observable
import kotlin.collections.ArrayList

class IssuesRepository {

    private val githubApi = GithubApi.create()

    fun getAllIssues() : Observable<ArrayList<IssueModel>> {
        return githubApi.issues("all", 100)
    }

    fun getIssueById(id: Int) : Observable<ExtendedIssueModel> {
        return githubApi.issueById(id)
    }
}