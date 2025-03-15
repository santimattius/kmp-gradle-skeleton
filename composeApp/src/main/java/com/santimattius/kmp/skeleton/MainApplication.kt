package com.santimattius.kmp.skeleton

import android.app.Application
import com.santimattius.kmp.skeleton.shared.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(sharedModule())
        }
    }
}