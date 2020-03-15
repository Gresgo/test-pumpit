package com.test.pumpit.ui.issues

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.pumpit.models.ExtendedIssueModel
import com.test.pumpit.models.IssueModel
import com.test.pumpit.providers.GithubRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class IssuesViewModel : ViewModel() {

    val issuesList = MutableLiveData<List<IssueModel>>()
    val currentIssue = ObservableField<ExtendedIssueModel>()
    private val repository = GithubRepositoryProvider.provideRepository()

    init {
        loadIssues()
    }

    fun loadIssues() {
        repository.getAllIssues()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                issuesList.value = result.filter {
                    it.pull_request == null //filter all pull requests out
                }
                Log.i("github", "issues loading successful")
            }, { error ->
                Log.i("github", "issues loading failed")
                error.printStackTrace()
            })
    }

    fun loadCurrentIssue(number: Int) {
//        if (currentIssue.value.number == number) return  //disable reload
        repository.getIssueById(number)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                currentIssue.set(result)
                Log.i("github", "issue loading successful")
            }, { error ->
                Log.i("github", "issue loading failed")
                error.printStackTrace()
            })
    }

}