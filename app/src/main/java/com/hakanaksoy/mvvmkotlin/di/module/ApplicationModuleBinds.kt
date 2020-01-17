package com.hakanaksoy.mvvmkotlin.di.module


import com.hakanaksoy.mvvmkotlin.app.initializers.AppInitializer
import com.hakanaksoy.mvvmkotlin.app.initializers.LoggerInitializer
import com.hakanaksoy.mvvmkotlin.app.initializers.StethoInitializer
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class ApplicationModuleBinds {

    @IntoSet
    @Binds
    abstract fun provideLogger(bind: LoggerInitializer): AppInitializer

    @IntoSet
    @Binds
    abstract fun provideStetho(bind: StethoInitializer): AppInitializer

}