package com.test.pumpit.ui.youdrive

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.pumpit.models.CarModel
import com.test.pumpit.providers.YoudriveRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class YoudriveViewModel : ViewModel() {

    val carsList = MutableLiveData<ArrayList<CarModel>>()
    private val repository = YoudriveRepositoryProvider.provideRepository()

    /**
     * runs when vm is created
     */
    init {
        loadCars()
    }

    /**
     * get data from api with rx
     */
    fun loadCars() {
        repository.getCars()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                carsList.value = ArrayList(sortToEkb(result.cars))
                Log.i("youdrive", "cars loading successful")
            }, { error ->
                Log.i("youdrive", "cars loading failed")
                error.printStackTrace()
            })
    }

    private fun sortToEkb(list: ArrayList<CarModel>) : List<CarModel> {
        return list.filter {
            /**
             * чуть больше чем Екатеринбург
             * практически получилось
             */
            (it.latitude in 56.7..56.95) && (it.longitude in 60.3..60.85)
        }
    }

}