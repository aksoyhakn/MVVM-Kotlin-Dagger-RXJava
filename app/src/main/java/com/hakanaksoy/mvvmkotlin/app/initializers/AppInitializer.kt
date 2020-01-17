package com.hakanaksoy.mvvmkotlin.app.initializers

import android.app.Application

interface AppInitializer {
    fun init(application: Application)
}
