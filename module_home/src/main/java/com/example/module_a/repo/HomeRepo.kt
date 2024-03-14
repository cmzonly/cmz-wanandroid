package com.example.module_a.repo

import com.example.common_lib.base.BaseLiveData
import com.example.common_lib.base.BaseRepository
import com.example.common_lib.util.Constants
import com.example.module_a.api.HomeApi
import com.example.module_a.bean.Article
import com.example.module_a.bean.Banner

class HomeRepo(private val homeApi: HomeApi) : BaseRepository() {
    suspend fun getBanner(data : BaseLiveData<List<Banner>>){
        requestResp({homeApi.getBanner()} , data)
    }

    suspend fun getArticleList(currentPage : Int , data: BaseLiveData<Article>){
        requestResp({homeApi.getHomeList(currentPage , Constants.DEFAULT_PAGER_SIZE)} , data)

    }

    //收藏
    suspend fun collect(id : Int , data: BaseLiveData<String>){
        requestResp({homeApi.collect(id)} , data)
    }

    //取消收藏
    suspend fun uncollect(id: Int , data: BaseLiveData<String>){
        requestResp({homeApi.unCollect(id)} , data)
    }

}