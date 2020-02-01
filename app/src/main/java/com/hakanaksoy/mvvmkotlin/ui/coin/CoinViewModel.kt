package com.hakanaksoy.mvvmkotlin.ui.coin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hakanaksoy.mvvmkotlin.service.APIHelper
import com.hakanaksoy.mvvmkotlin.ui.base.BaseViewModel
import com.hakanaksoy.mvvmkotlin.ui.base.Resource
import com.hakanaksoy.mvvmkotlin.ui.base.Status
import com.hakanaksoy.mvvmkotlin.ui.home.CoinViewState
import com.uber.autodispose.autoDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CoinViewModel@Inject constructor(apiHelper: APIHelper) : BaseViewModel(apiHelper) {

    private val coinLiveData = MutableLiveData<CoinViewState>()

    fun getCoinLiveData(): LiveData<CoinViewState> = coinLiveData

    fun getCoin(){
        Log.d("home_hakan","getCoin")
        baseApiHelper?.let { baseApi ->
            baseApi.getCoin()
                .subscribeOn(Schedulers.io())
                .map { Resource.success(it) }
                .onErrorReturn { Resource.error(it) }
                .doOnSubscribe { progressLiveData.postValue(true) }
                .doOnTerminate { progressLiveData.postValue(false) }
                .observeOn(AndroidSchedulers.mainThread())
                .autoDisposable(this)
                .subscribe {
                    when (it?.status) {
                        Status.SUCCESS -> {
                            Log.d("home_hakan","succes")
                            coinLiveData.value= CoinViewState(
                                status = it.status,
                                error = it.error,
                                data = it.data
                            )
                        }
                        Status.ERROR -> {
                            Timber.e(it.error)
                            Log.d("home_hakan",it.error.toString())
                        }
                    }
                }
        }
    }
}