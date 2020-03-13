package com.test.pumpit.ui.issues

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.pumpit.models.IssueModel
import com.test.pumpit.providers.GithubRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class IssuesViewModel : ViewModel() {

    val issuesList = MutableLiveData<ArrayList<IssueModel>>()
    val currentIssue = MutableLiveData<IssueModel>()
    private val repository = GithubRepositoryProvider.provideRepository()

    init {
        loadIssues()
    }

    fun loadIssues() {
        repository.getAllIssues()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                issuesList.value = result
                Log.i("github", "issues loading successful")
            }, { error ->
                Log.i("github", "issues loading failed")
                error.printStackTrace()
            })
    }

    fun loadCurrentIssue(number: Int) {
        repository.getIssueById(number)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                currentIssue.value = result[0]
                Log.i("github", "issue loading successful")
            }, { error ->
                Log.i("github", "issue loading failed")
                error.printStackTrace()
            })
    }

}