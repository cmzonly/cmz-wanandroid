package com.example.module_search.repo

import com.example.common_lib.base.BaseLiveData
import com.example.common_lib.base.BaseRepository
import com.example.common_lib.base.BaseResp
import com.example.module_search.api.SearchApi
import com.example.module_search.bean.SearchBean
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow

class SearchRepo(private val searchApi: SearchApi) : BaseRepository() {
    suspend fun search(k : String, data: BaseLiveData<SearchBean>){
        requestResp({searchApi.search(k)} , data)
    }

    fun searchFlow(k : String) : Flow<BaseResp<SearchBean>> {
        return flow {
            val resp = searchApi.search(k)
            emit(resp)
        }.debounce(1000)
    }
}