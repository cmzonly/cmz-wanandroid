package com.example.common_lib.view

import android.content.Context
import com.example.common_lib.R
import com.lxj.xpopup.core.CenterPopupView

class MyPopup(context: Context) : CenterPopupView(context) {
    override fun getImplLayoutId(): Int {
        return R.layout.popup_loading
    }

    override fun onCreate() {
        super.onCreate()
    }
}