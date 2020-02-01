package com.hakanaksoy.mvvmkotlin.service

import com.hakanaksoy.mvvmkotlin.service.response.Coin
import com.hakanaksoy.mvvmkotlin.service.response.Personel
import io.reactivex.Observable
import retrofit2.http.GET

interface APIHelper {


    @GET("getPersonelList")
    fun getDataList(): Observable<Personel>

    @GET("markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1")
    fun getCoin(): Observable<List<Coin>>

}