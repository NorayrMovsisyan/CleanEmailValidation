package com.example.cleanemailvalidation.presentation

import android.app.Application
import com.example.cleanemailvalidation.mainModule
import com.example.cleanemailvalidation.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(viewModelModule, mainModule))
        }
    }
}