package com.example.only

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common_lib.util.LoginUtil
import com.example.common_lib.util.StatusBarUtil
import com.example.common_lib.util.ToastUtil
import com.example.module_a.di.homeModule
import com.example.module_c.di.meModule
import com.example.module_search.di.searchModule
import com.tencent.mmkv.MMKV
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class OnlyApplication : Application() {
    private val modules = mutableListOf(homeModule, meModule , searchModule)

    override fun onCreate() {
        super.onCreate()
        ToastUtil.init(this)
        StatusBarUtil.init(this)
        LoginUtil.init(this)
        initARouter()
        initMMKV()
        startKoin {
            androidLogger()
            androidContext(this@OnlyApplication)
            modules(modules)

        }
    }

    private fun initMMKV() {
        MMKV.initialize(this)
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}