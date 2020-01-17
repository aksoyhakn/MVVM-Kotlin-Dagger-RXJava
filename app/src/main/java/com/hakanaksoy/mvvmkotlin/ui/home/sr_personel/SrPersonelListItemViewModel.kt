package com.hakanaksoy.mvvmkotlin.ui.home.sr_personel

import androidx.databinding.ObservableField
import com.hakanaksoy.mvvmkotlin.base.BaseViewModel
import com.hakanaksoy.mvvmkotlin.service.response.SrPersonel


class SrPersonelListItemViewModel : BaseViewModel() {

    var item = ObservableField<SrPersonel>()
}