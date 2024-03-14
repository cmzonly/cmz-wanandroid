package com.example.module_c.di

import com.example.common_lib.network.RetrofitManager
import com.example.module_c.api.MeApi
import com.example.module_c.repo.MeRepo
import com.example.module_c.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val meModule = module {
    single { RetrofitManager.create<MeApi>() }
    single { MeRepo(get()) }
    viewModel { LoginViewModel(get()) }
}