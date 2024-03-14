package com.example.common_lib.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.common_lib.view.MyPopup
import com.gyf.immersionbar.ImmersionBar
import com.lxj.xpopup.XPopup

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    val TAG: String = this.javaClass.simpleName

    lateinit var databinding : T

    lateinit var myCenterPopup: MyPopup

    abstract fun init()

    abstract fun observe()

    abstract fun getLayoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databinding = DataBindingUtil.inflate(inflater , getLayoutId() , container , false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initStatusBar()
        observe()
        init()
    }

    open fun initStatusBar(alpha : Float = 0.0f , isDarkFont : Boolean = true){
        ImmersionBar.with(this)
            .statusBarAlpha(alpha)
            .statusBarDarkFont(isDarkFont)
            .init()
    }

    fun showPopup(){
        myCenterPopup = MyPopup(requireActivity())
        XPopup.Builder(requireActivity())
            .asCustom(myCenterPopup)
            .show()
    }

    fun dismissPopup(){
        myCenterPopup.dismiss()
    }

}