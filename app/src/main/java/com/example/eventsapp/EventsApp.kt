package com.example.eventsapp

import android.app.Application
import com.example.eventsapp.di.networkModule
import com.example.eventsapp.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@ExperimentalCoroutinesApi
class EventsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@EventsApp)
            modules(networkModule, viewModelModule)
        }
    }
}