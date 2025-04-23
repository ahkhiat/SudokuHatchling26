package com.devid_academy.sudokuhatchling26.di

import android.content.Context
import android.content.SharedPreferences
import com.devid_academy.sudokuhatchling26.SHARED_PREFS
import com.devid_academy.sudokuhatchling26.logic.data.network.SupabaseModule
import com.devid_academy.sudokuhatchling26.logic.viewmodel.LoginViewModel
import com.devid_academy.sudokuhatchling26.logic.viewmodel.RegisterViewModel
import com.devid_academy.sudokuhatchling26.logic.viewmodel.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val databaseModule = module {
    singleOf(::SupabaseModule)
}

val viewModelsModule = module {
    viewModel { RegisterViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}



