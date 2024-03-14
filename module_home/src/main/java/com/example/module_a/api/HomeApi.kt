package com.example.module_a.api

import com.example.common_lib.base.BaseResp
import com.example.module_a.bean.Article
import com.example.module_a.bean.Banner
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeApi {

    /**
     *
     */
    @GET("banner/json")
    suspend fun getBanner() : BaseResp<List<Banner>>

    /**
     * 首页文章列表  /article/list/0/json
     */
    @GET("article/list/{page}/json")
    suspend fun getHomeList(
        @Path("page") page : Int ,
        @Query("page_size") page_size:Int
    ) : BaseResp<Article>

    /**
     * 收藏-首页文章列表的文章
     */
    @POST("lg/collect/{id}/json")
    suspend fun collect(
        @Path("id") id : Int
    ):BaseResp<String>


    /**
     * 取消收藏-首页文章列表
     */
    @POST("lg/uncollect_originId/{id}/json")
    suspend fun unCollect(
        @Path("id") id: Int
    ) : BaseResp<String>




}