package com.test.pumpit.ui.youdrive

import android.util.Log
import com.test.pumpit.models.YoudriveResponse
import io.reactivex.Observable

class YoudriveRepository {

    /**
     * create retrofit instance and get data
     */
    private val apiService = YoudriveApi.create()

    fun getCars() : Observable<YoudriveResponse> {
        Log.i("youdrive", "cars loading started")
        return apiService.cars()
    }
}