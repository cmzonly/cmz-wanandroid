package com.example.common_lib.base

import android.util.Log
import androidx.lifecycle.Observer

 open class BaseStateObserver<T> : Observer<BaseResp<T>>{
    override fun onChanged(value: BaseResp<T>) {
        when (value.responseState) {
            BaseResp.ResponseState.REQUEST_START -> {
                Log.d("BaseStateObserver", "Observer: start")
                getRespDataStart()
            }

            BaseResp.ResponseState.REQUEST_SUCCESS -> {
                Log.d("BaseStateObserver", "Observer: success")
                if (value.data == null) {
                    getRespSuccess()
                } else {
                    getRespDataSuccess(value.data)
                }

            }

            BaseResp.ResponseState.REQUEST_FAILED -> {
                Log.d("BaseStateObserver", "Observer: failed")
                getRespDataEnd()
            }

            BaseResp.ResponseState.REQUEST_ERROR -> {
                Log.d("BaseStateObserver", "Observer: error")
                getRespDataEnd()
            }

            else -> {}
        }
    }

    open fun getRespDataStart() {}
    open fun getRespDataSuccess(it: T) {}
    open fun getRespSuccess() {}
    open fun getRespDataEnd() {}
}