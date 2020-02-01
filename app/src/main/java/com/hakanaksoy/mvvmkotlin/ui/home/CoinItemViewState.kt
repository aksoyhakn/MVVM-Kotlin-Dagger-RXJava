package com.hakanaksoy.mvvmkotlin.ui.home

import com.hakanaksoy.mvvmkotlin.service.response.Coin

class CoinItemViewState(private val tvShow: Coin) {

    fun getImageUrl() = tvShow.image

    fun getName() = tvShow.name

    fun getLastUpdated() = tvShow.lastUpdated
}