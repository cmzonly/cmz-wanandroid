package com.example.common_lib.base

import androidx.lifecycle.MutableLiveData

class BaseLiveData<T> : MutableLiveData<BaseResp<T>>() {
}