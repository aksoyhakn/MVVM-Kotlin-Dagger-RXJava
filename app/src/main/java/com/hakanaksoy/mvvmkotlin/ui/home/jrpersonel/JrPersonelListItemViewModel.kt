package com.hakanaksoy.mvvmkotlin.ui.home.jrpersonel

import androidx.databinding.ObservableField
import com.hakanaksoy.mvvmkotlin.ui.base.BaseViewModel
import com.hakanaksoy.mvvmkotlin.service.response.JrPersonel


class JrPersonelListItemViewModel : BaseViewModel() {

    var item = ObservableField<JrPersonel>()
}