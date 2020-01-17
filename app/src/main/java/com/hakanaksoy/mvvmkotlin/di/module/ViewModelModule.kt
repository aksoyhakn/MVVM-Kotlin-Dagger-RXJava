package com.hakanaksoy.mvvmkotlin.di.module


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hakanaksoy.mvvmkotlin.di.key.ViewModelKey
import com.hakanaksoy.mvvmkotlin.di.scope.ViewModelFactory
import com.hakanaksoy.mvvmkotlin.ui.dashboard.DashboardViewModel
import com.hakanaksoy.mvvmkotlin.ui.home.HomeViewModel
import com.hakanaksoy.mvvmkotlin.ui.splash.SplashActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(SplashActivityViewModel::class)
    abstract fun provideSplashViewModel(splashActivityViewModel: SplashActivityViewModel): ViewModel


    @IntoMap
    @Binds
    @ViewModelKey(DashboardViewModel::class)
    abstract fun provideDashboardViewModel(dashboardViewModel: DashboardViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideHomeViewModel(homeViewModel: HomeViewModel): ViewModel




}