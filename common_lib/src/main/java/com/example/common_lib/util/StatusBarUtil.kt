package com.example.common_lib.util

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

object StatusBarUtil {

    lateinit var mContext : Application

    fun init(context : Application) {
        mContext = context
    }

    @SuppressLint("DiscouragedApi", "InternalInsetResource")
    fun getStatusBarUtil() : Int{
        val resourceId = mContext.resources.getIdentifier("status_bar_height", "dimen", "android")
        return mContext.resources.getDimensionPixelSize(resourceId)
    }

}