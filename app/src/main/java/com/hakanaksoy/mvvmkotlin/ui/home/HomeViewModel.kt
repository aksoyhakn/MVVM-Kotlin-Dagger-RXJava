package com.hakanaksoy.mvvmkotlin.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hakanaksoy.mvvmkotlin.base.BaseViewModel
import com.hakanaksoy.mvvmkotlin.base.Resource
import com.hakanaksoy.mvvmkotlin.base.Status
import com.hakanaksoy.mvvmkotlin.service.APIHelper
import com.hakanaksoy.mvvmkotlin.service.response.Personel
import com.uber.autodispose.android.autoDisposable
import com.uber.autodispose.android.lifecycle.autoDisposable
import com.uber.autodispose.autoDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class HomeViewModel @Inject constructor(apiHelper: APIHelper) : BaseViewModel(apiHelper) {


    private val _personelLiveData = MutableLiveData<Resource<Personel>>()
    val personelLiveData: LiveData<Resource<Personel>> get() = _personelLiveData


    fun getJrPersonel() {
        baseApiHelper?.let { baseApi ->
            baseApi.getDataList()
                    .subscribeOn(Schedulers.io())
                    .map { Resource.success(it) }
                    .onErrorReturn { Resource.error(it) }
                    .doOnSubscribe { progressLiveData.postValue(true) }
                    .doOnTerminate { progressLiveData.postValue(false) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .autoDisposable(this)
                    .subscribe {
                        when (it?.status) {
                            Status.SUCCESS -> _personelLiveData.postValue(it)
                            Status.ERROR -> Timber.e(it.error)
                        }
                    }
        }
    }


}