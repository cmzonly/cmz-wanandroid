package com.example.module_c.repo

import com.example.common_lib.base.BaseLiveData
import com.example.common_lib.base.BaseRepository
import com.example.module_c.bean.LoginBean
import com.example.module_c.bean.RegisterBean
import com.example.module_c.api.MeApi

class MeRepo(private val meApi: MeApi) : BaseRepository() {
    suspend fun login(userName: String, password: String, data: BaseLiveData<LoginBean>) {
        requestResp({ meApi.login(userName, password) }, data)
    }

    suspend fun register(
        username: String,
        password: String,
        repassword: String,
        data: BaseLiveData<RegisterBean>
    ) {
        requestResp({ meApi.register(username, password, repassword) }, data)
    }
}