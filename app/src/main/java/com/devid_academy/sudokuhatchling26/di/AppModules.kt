package com.devid_academy.sudokuhatchling26.di

import android.content.Context
import android.content.SharedPreferences
import com.devid_academy.sudokuhatchling26.SHARED_PREFS
import com.devid_academy.sudokuhatchling26.data.manager.PreferencesManager
import com.devid_academy.sudokuhatchling26.data.network.SupabaseModule
import com.devid_academy.sudokuhatchling26.ui.register.RegisterViewModel
import com.devid_academy.sudokuhatchling26.ui.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val databaseModule = module {
    singleOf(::SupabaseModule)
}

val viewModelsModule = module {
    viewModel { RegisterViewModel(get()) }
    viewModel { SplashViewModel(get(), get()) }
}

val preferencesModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
    }
    single {
        PreferencesManager(get(), get())
    }
}

