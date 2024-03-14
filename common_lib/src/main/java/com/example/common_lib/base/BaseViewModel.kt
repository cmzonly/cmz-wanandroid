package com.example.common_lib.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.internal.Util

typealias vmBlock = suspend () ->Unit

open class BaseViewModel : ViewModel() {

    fun launch(block: vmBlock){
        viewModelScope.launch() {
            try {
                block.invoke()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    private fun onError(e : Exception) {
        Log.d("onError", "onError: $e")
    }
}