package com.hakanaksoy.mvvmkotlin.ui.base

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.hakanaksoy.mvvmkotlin.service.APIHelper

open class BaseViewModel(var baseApiHelper: APIHelper? = null) : AutoDisposeViewModel() {

    var progressLiveData = MutableLiveData<Boolean>()
    var toastLiveData = MutableLiveData<String>()

    lateinit var mAuth: FirebaseAuth

    private fun initFirebase() {
        mAuth = FirebaseAuth.getInstance()
    }

    init {
        initFirebase()
    }


}
