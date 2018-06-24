package com.example.alex.jokesfrontend

import android.app.Application
import com.example.alex.jokesfrontend.di.httpModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(httpModule))
        Timber.plant(Timber.DebugTree())
    }
}