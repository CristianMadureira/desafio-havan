package com.example.desafiohavan

import android.app.Application
import com.example.desafiohavan.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                domainModule,
                presentationModule,
                dataModule,
                serviceModule,
                daoModule
            )
        }
    }
}