package com.example.module_a.di
import com.example.common_lib.network.RetrofitManager
import com.example.module_a.api.HomeApi
import com.example.module_a.repo.HomeRepo
import com.example.module_a.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by stew on 8/1/22.
 * mail: stewforani@gmail.com
 */
val homeModule = module {
    single { RetrofitManager.create<HomeApi>() }
    single { HomeRepo(get()) }
    viewModel { HomeViewModel(get()) }
}