package com.hakanaksoy.mvvmkotlin.ui.home.jr_personel

import androidx.databinding.ObservableField
import com.hakanaksoy.mvvmkotlin.base.BaseViewModel
import com.hakanaksoy.mvvmkotlin.service.response.JrPersonel


class JrPersonelListItemViewModel : BaseViewModel() {

    var item = ObservableField<JrPersonel>()
}