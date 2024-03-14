package com.example.common_lib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.common_lib.view.MyPopup
import com.gyf.immersionbar.ImmersionBar
import com.lxj.xpopup.XPopup

//import com.lxj.xpopup.XPopup

abstract class BaseActivity<T : ViewDataBinding>(): AppCompatActivity() {
    lateinit var databinding : T

    abstract fun getLayoutId(): Int

    abstract fun init()

    lateinit var myCenterPopup: MyPopup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this , getLayoutId())
        initStatusBar()
        init()
        observe()
    }

    open fun observe() {}

    override fun onDestroy() {
        super.onDestroy()
        databinding.unbind()

    }

    open fun initStatusBar(alpha : Float = 0.0f , isDarkFont : Boolean = true){
        ImmersionBar.with(this)
            .statusBarAlpha(alpha)
            .statusBarDarkFont(isDarkFont)
            .init()
    }

    fun showPopup(){
        myCenterPopup = MyPopup(this)
        XPopup.Builder(this)
            .asCustom(myCenterPopup)
            .show()
    }

    fun dismissPopup(){
        myCenterPopup.dismiss()
    }


}