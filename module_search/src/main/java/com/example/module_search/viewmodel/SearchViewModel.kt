package com.example.module_search.viewmodel

import com.example.common_lib.base.BaseLiveData
import com.example.common_lib.base.BaseViewModel
import com.example.module_search.bean.SearchBean
import com.example.module_search.repo.SearchRepo

class SearchViewModel(val searchRepo: SearchRepo) : BaseViewModel() {
    val searchLiveData = BaseLiveData<SearchBean>()

    fun search(k : String){
        launch {
            searchRepo.search(k , searchLiveData)
        }
    }
}