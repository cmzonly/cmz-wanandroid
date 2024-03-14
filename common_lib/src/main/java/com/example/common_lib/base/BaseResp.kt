package com.example.common_lib.base

class BaseResp<T> {
    val errorCode: Int = -2
    val errorMsg: String = ""
    val data: T? = null
    var responseState: ResponseState? = null

    enum class ResponseState {
        REQUEST_START,
        REQUEST_SUCCESS,
        REQUEST_FAILED,
        REQUEST_ERROR
    }
}