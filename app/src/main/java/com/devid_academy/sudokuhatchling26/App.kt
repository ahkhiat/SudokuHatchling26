package com.devid_academy.sudokuhatchling26

import android.app.Application
import com.devid_academy.sudokuhatchling26.di.databaseModule
import com.devid_academy.sudokuhatchling26.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                databaseModule,
                viewModelsModule,
            )
        }
    }
}