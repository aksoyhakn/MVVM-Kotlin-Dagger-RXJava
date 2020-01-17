package com.hakanaksoy.mvvmkotlin.di.module

import com.furkanaskin.app.personal.di.scope.ActivityScope
import com.hakanaksoy.mvvmkotlin.ui.dashboard.DashboardActivity
import com.hakanaksoy.mvvmkotlin.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun splashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun dasboardActivity(): DashboardActivity


}