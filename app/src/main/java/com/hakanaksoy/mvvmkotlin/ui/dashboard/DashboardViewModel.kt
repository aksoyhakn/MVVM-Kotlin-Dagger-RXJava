package com.hakanaksoy.mvvmkotlin.ui.dashboard


import com.hakanaksoy.mvvmkotlin.ui.base.BaseViewModel
import com.hakanaksoy.mvvmkotlin.service.APIHelper
import javax.inject.Inject


class DashboardViewModel @Inject constructor(apiHelper: APIHelper) : BaseViewModel(apiHelper)