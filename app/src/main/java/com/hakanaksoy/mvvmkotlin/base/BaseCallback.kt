package com.hakanaksoy.mvvmkotlin.base

interface BaseCallBack<T> {
    fun onSuccess(data: T)
    fun onFail(message: String)
}