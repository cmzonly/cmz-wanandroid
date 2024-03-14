package com.example.module_search.api

import com.example.common_lib.base.BaseResp
import com.example.module_search.bean.SearchBean
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {

    /**
     * 搜索
     */
    @POST("/article/query/{page}/json")
    suspend fun search(
        @Query("k") k: String
    ): BaseResp<SearchBean>
}