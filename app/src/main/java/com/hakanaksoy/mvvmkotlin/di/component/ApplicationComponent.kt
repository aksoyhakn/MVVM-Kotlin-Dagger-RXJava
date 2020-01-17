package com.hakanaksoy.mvvmkotlin.di.component

import android.app.Application
import com.hakanaksoy.mvvmkotlin.app.App
import com.hakanaksoy.mvvmkotlin.di.module.ActivityModule
import com.hakanaksoy.mvvmkotlin.di.module.ApplicationModule
import com.hakanaksoy.mvvmkotlin.di.module.NetModule
import com.hakanaksoy.mvvmkotlin.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetModule::class,
        ActivityModule::class,
        ViewModelModule::class
    ]
)

interface ApplicationComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationBind(application: Application): Builder

        fun build(): ApplicationComponent
    }

}
