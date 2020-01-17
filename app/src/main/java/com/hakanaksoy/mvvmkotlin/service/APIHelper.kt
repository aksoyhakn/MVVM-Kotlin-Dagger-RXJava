package com.hakanaksoy.mvvmkotlin.service

import com.hakanaksoy.mvvmkotlin.service.response.Personel
import io.reactivex.Observable
import retrofit2.http.GET

interface APIHelper {


    @GET("getPersonelList")
    fun getDataList(): Observable<Personel>

}