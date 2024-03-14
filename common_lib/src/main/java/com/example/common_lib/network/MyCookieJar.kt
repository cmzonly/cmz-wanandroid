package com.example.common_lib.network

import com.example.common_lib.bean.CookieBean
import com.example.common_lib.util.Constants
import com.example.common_lib.util.MMKVUtil
import com.google.gson.Gson
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class MyCookieJar : CookieJar {

    private val gson = Gson()


    /**
     *  //Http发送请求前回调，Request中设置Cookie
     */
    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val s = MMKVUtil.getString(Constants.USER_COOKIE)
        if (s != null) {
            return gson.fromJson(s, CookieBean::class.java).list
        }

        return arrayListOf()

    }

    /**
     *  //Http请求结束，Response中有Cookie时候回调
     */
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        //如果有保存过cookie,则不处理
        if (MMKVUtil.getString(Constants.USER_COOKIE) != null){
            return
        }

        for (cookie in cookies) {
            if (cookie.toString().contains("loginUserName")){
                MMKVUtil.put(Constants.USER_COOKIE , gson.toJson(CookieBean(cookies)))
                break
            }
        }


    }
}