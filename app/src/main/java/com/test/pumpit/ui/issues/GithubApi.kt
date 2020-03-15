package com.test.pumpit.ui.issues

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.pumpit.models.ExtendedIssueModel
import com.test.pumpit.models.IssueModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    companion object Factory {
        /**
         * if you need to remove unimportant data from model use this gson in GsonConverterFactory
         */
        private fun getGson() : Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

        fun create() : GithubApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com")
                .build()

            return retrofit.create(GithubApi::class.java)
        }
    }

    /**
     * maybe 2 requests are better but i like this way more
     */

    @GET("/repos/android/sunflower/issues/{number}")
    fun issueById(
        @Path("number")
        number: Int
    ) : Observable<ExtendedIssueModel>

    @GET("/repos/android/sunflower/issues")
    fun issues(
        @Query("state")
        state: String
    ) : Observable<ArrayList<IssueModel>>
}