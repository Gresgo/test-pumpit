package com.test.pumpit.ui.youdrive

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.pumpit.models.YoudriveResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface YoudriveApi {

    companion object Factory {

        /**
         * if you need to remove unimportant data from model use this gson in GsonConverterFactory
         */
        private fun getGson() : Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

        fun create() : YoudriveApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://youdrive.today")
                .build()

            return retrofit.create(YoudriveApi::class.java)
        }
    }

    @GET("/info")
    fun cars() : Observable<YoudriveResponse>
}