package com.example.cashassignment.di

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinApplication : Application() {

    companion object {
        lateinit var instance: KoinApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger()
            androidContext(this@KoinApplication)
            modules(appModule)
            modules(viewModelModule)
        }
    }

    fun context(): Context = applicationContext
}