package com.example.common_lib.base

import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common_lib.util.Constants
import com.example.common_lib.util.ToastUtil
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

open class BaseRepository {
    suspend fun <T> requestResp(
        block: suspend () -> BaseResp<T>,
        liveData: BaseLiveData<T>
    ) {
        var result = BaseResp<T>()
        result.responseState = BaseResp.ResponseState.REQUEST_START
        liveData.value = result

        try {
            result = block.invoke()
            when (result.errorCode) {
                Constants.HTTP_SUCCESS -> {
                    result.responseState = BaseResp.ResponseState.REQUEST_SUCCESS
                }
                Constants.HTTP_NO_LOGIN -> {
                    result.responseState = BaseResp.ResponseState.REQUEST_FAILED
                    ToastUtil.showMsg("请先登录")
                    ARouter.getInstance()
                        .build(Constants.PATH_LOGIN)
                        .navigation()
                }

                else -> {
                    result.responseState = BaseResp.ResponseState.REQUEST_FAILED
                    ToastUtil.showMsg("${result.errorCode}: ${result.errorMsg}")
                }
            }
        } catch (e: Exception) {
            when (e) {
                is UnknownHostException,
                is HttpException,
                is ConnectException,->{
                    //网络error
                    ToastUtil.showMsg("网络错误！")
                }
                else -> {
                    ToastUtil.showMsg("未知错误！")
                }
            }
            result.responseState = BaseResp.ResponseState.REQUEST_ERROR
        }finally {
            Log.d("TestSus", "br4")
            liveData.value = result
        }
    }
}