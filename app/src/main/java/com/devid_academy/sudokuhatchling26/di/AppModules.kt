package com.devid_academy.sudokuhatchling26.di

import com.devid_academy.sudokuhatchling26.logic.data.network.SupabaseModule
import com.devid_academy.sudokuhatchling26.logic.data.repository.GameRepository
import com.devid_academy.sudokuhatchling26.logic.data.repository.UserRepository
import com.devid_academy.sudokuhatchling26.logic.viewmodel.ChooseLevelViewModel
import com.devid_academy.sudokuhatchling26.logic.viewmodel.CompletedViewModel
import com.devid_academy.sudokuhatchling26.logic.viewmodel.GameViewModel
import com.devid_academy.sudokuhatchling26.logic.viewmodel.HomeViewModel
import com.devid_academy.sudokuhatchling26.logic.viewmodel.LoginViewModel
import com.devid_academy.sudokuhatchling26.logic.viewmodel.RegisterViewModel
import com.devid_academy.sudokuhatchling26.logic.viewmodel.SplashViewModel
import com.devid_academy.sudokuhatchling26.logic.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val databaseModule = module {
    single { SupabaseModule() }
    single { SupabaseModule().client }
    single { UserRepository(get()) }
    single { GameRepository(get()) }
}


val viewModelsModule = module {
    viewModel { RegisterViewModel(get(), get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { ChooseLevelViewModel(get()) }
    viewModel { GameViewModel(get()) }
    viewModel { UserViewModel(get()) }
    viewModel { CompletedViewModel(get(), get()) }
    viewModel { HomeViewModel(get()) }
}



