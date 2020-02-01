package com.hakanaksoy.mvvmkotlin.ui.splash

import androidx.databinding.ObservableField
import com.hakanaksoy.mvvmkotlin.ui.base.BaseViewModel
import com.hakanaksoy.mvvmkotlin.service.APIHelper
import javax.inject.Inject
import org.jetbrains.anko.doAsync

class SplashActivityViewModel @Inject constructor(apiHelper: APIHelper) : BaseViewModel(apiHelper) {

    var loginSuccess: ObservableField<Boolean> = ObservableField(false)
    var afterRegisterSuccess: ObservableField<Boolean> = ObservableField(false)

    init {
        alreadyLogged()
    }

    private fun alreadyLogged() {
        doAsync {
            val firebaseID = mAuth.currentUser?.uid
            val user = firebaseID?.let {  }
        }
    }
}