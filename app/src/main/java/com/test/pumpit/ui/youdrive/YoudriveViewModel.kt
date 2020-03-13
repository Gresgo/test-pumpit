package com.test.pumpit.ui.youdrive

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.pumpit.models.CarModel
import com.test.pumpit.providers.YoudriveRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class YoudriveViewModel : ViewModel() {

    //val carsList = ObservableField<ArrayList<CarModel>>()
    val carsList = MutableLiveData<ArrayList<CarModel>>()
    private val repository = YoudriveRepositoryProvider.provideRepository()

    /**
     * get data from api with rx
     */
    init {
        loadCars()
    }

    fun loadCars() {
        repository.getCars()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                //carsList.set(result.cars)
                carsList.value = result.cars
                Log.i("youdrive", "cars loading successful")
            }, { error ->
                Log.i("youdrive", "cars loading failed")
                error.printStackTrace()
            })
    }

}