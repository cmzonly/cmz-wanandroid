package com.example.module_c.viewmodel

import com.example.common_lib.base.BaseLiveData
import com.example.common_lib.base.BaseViewModel
import com.example.module_c.bean.LoginBean
import com.example.module_c.bean.RegisterBean
import com.example.module_c.repo.MeRepo
import okhttp3.ResponseBody

class LoginViewModel(private val meRepo: MeRepo) : BaseViewModel() {
     var loginLiveData = BaseLiveData<LoginBean>()

     var registerLiveData = BaseLiveData<RegisterBean>()

    fun login(username: String, password: String) {
        launch {
            meRepo.login(username, password, loginLiveData)
        }
    }

    fun register(username: String, password: String , repassword: String){
        launch {
            meRepo.register(username , password , repassword , registerLiveData)
        }
    }

}