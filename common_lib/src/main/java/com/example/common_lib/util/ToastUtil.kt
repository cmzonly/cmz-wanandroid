package com.example.common_lib.util

import android.app.Application
import android.content.Context
import android.widget.Toast

object ToastUtil {
    lateinit var mContext : Application

    fun init(context: Application){
        mContext = context
    }

    fun showMsg(msg : String){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
    }
}