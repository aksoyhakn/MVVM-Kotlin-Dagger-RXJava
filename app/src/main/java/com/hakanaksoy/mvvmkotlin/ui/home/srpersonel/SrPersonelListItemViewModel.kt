package com.hakanaksoy.mvvmkotlin.ui.home.srpersonel

import androidx.databinding.ObservableField
import com.hakanaksoy.mvvmkotlin.ui.base.BaseViewModel
import com.hakanaksoy.mvvmkotlin.service.response.SrPersonel


class SrPersonelListItemViewModel : BaseViewModel() {

    var item = ObservableField<SrPersonel>()
}