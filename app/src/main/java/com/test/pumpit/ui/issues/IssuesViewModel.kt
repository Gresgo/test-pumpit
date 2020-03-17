package com.test.pumpit.ui.issues

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.picasso.Picasso
import com.test.pumpit.models.ExtendedIssueModel
import com.test.pumpit.models.IssueModel
import com.test.pumpit.providers.GithubRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.joda.time.DateTime
import java.text.DateFormatSymbols

class IssuesViewModel : ViewModel() {

    //TODO: pagination
    val issuesList = MutableLiveData<List<IssueModel>>()
    val currentIssue = ObservableField<ExtendedIssueModel>()
    private val repository = GithubRepositoryProvider.provideRepository()

    /**
     * called when vm is created
     */
    init {
        loadIssues()
    }

    /**
     * load all issues with rx
     */
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

    /**
     * load selected issue by id
     */
    fun loadCurrentIssue(number: Int) {
//        if (currentIssue.value.number == number) return  //disable reload
        repository.getIssueById(number)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                result.created_at = makeData(result.created_at!!)
                currentIssue.set(result)
                Log.i("github", "issue loading successful")
            }, { error ->
                Log.i("github", "issue loading failed")
                error.printStackTrace()
            })
    }

    private fun makeData(date: String) : String {
        val d = DateTime.parse(date)
        return d.dayOfMonth.toString() + " " + DateFormatSymbols().months[d.monthOfYear - 1] + ", " + d.year.toString()
    }

}

/**
 * binding picasso to load avatars
 */
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Picasso.get().load(imageUrl).into(view)
}