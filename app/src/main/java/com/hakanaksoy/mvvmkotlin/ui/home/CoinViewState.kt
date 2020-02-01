package com.hakanaksoy.mvvmkotlin.ui.home

import com.hakanaksoy.mvvmkotlin.ui.base.Status
import com.hakanaksoy.mvvmkotlin.service.response.Coin

class CoinViewState (
    val status: Status,
    val error: Throwable? = null,
    val data: List<Coin>? = null
) {
    fun getPopularTvShows() = data ?: mutableListOf()

    fun isLoading() = status == Status.LOADING

    fun getErrorMessage() = error?.message

    fun shouldShowErrorMessage() = error != null
}