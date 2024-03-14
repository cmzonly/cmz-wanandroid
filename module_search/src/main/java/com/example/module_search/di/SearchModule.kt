package com.example.module_search.di

import com.example.common_lib.network.RetrofitManager
import com.example.module_search.api.SearchApi
import com.example.module_search.repo.SearchRepo
import com.example.module_search.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    single { RetrofitManager.create<SearchApi>() }
    single { SearchRepo(get()) }
    viewModel { SearchViewModel(get()) }
}