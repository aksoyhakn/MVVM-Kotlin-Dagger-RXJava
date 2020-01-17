package com.hakanaksoy.mvvmkotlin.base

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.hakanaksoy.mvvmkotlin.service.APIHelper
import java.util.Locale

open class BaseViewModel(var baseApiHelper: APIHelper? = null) : AutoDisposeViewModel() {

    var progressLiveData = MutableLiveData<Boolean>()
    var toastLiveData = MutableLiveData<String>()

    lateinit var mAuth: FirebaseAuth

    lateinit var currentLocation: String

    private fun initFirebase() {
        mAuth = FirebaseAuth.getInstance()
    }

    init {
        initFirebase()
        getUser()
        getUserLocation()
    }

    fun getUser() {

    }

    private fun getUserLocation() {
        currentLocation = Locale.getDefault().country.toLowerCase()

        if (currentLocation.isEmpty())
            currentLocation = "us"
    }
}
