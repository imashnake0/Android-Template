package com.imashnake.template

import android.app.Application
import com.imashnake.template.data.di.networkModule
import com.imashnake.template.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(networkModule, uiModule)
        }
    }
}