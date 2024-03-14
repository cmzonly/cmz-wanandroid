package com.example.module_c.api

import com.example.common_lib.base.BaseResp
import com.example.module_c.bean.LoginBean
import com.example.module_c.bean.RegisterBean
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MeApi {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): BaseResp<LoginBean>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("user/register")
    suspend  fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String,
    ) : BaseResp<RegisterBean>


//    /**
//     * 注册
//     */
//    @FormUrlEncoded
//    @POST("user/register")
//    fun registerTest(
//        @Field("username") username: String,
//        @Field("password") password: String,
//        @Field("repassword") repassword: String,
//    ) : Call<ResponseBody>
}