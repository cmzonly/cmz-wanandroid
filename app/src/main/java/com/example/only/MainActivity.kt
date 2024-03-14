package com.example.only

import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.common_lib.base.BaseActivity
import com.example.common_lib.util.StatusBarUtil
import com.example.only.adapter.PagerAdapter
import com.example.only.databinding.ModuleHomeActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : BaseActivity<ModuleHomeActivityMainBinding>() {
    override fun getLayoutId() = R.layout.module_home_activity_main

    override fun init() {

        databinding.statusBar.layoutParams.height = StatusBarUtil.getStatusBarUtil()
        /**
         * 解决三个以上item的不显示文字问题
         */
        /**
         * 解决三个以上item的不显示文字问题
         */
        databinding.bnv.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED
//        /**
//         * 设置图标显示不完整
//         */
//        /**
//         * 设置图标显示不完整
//         */
//        databinding.bnv.itemIconTintList = null

        /**
         * 设置viewpager2适配器
         */
        databinding.pager2.adapter = PagerAdapter(this)


        databinding.bnv.setOnItemSelectedListener {
            when(it.itemId){
                R.id.item_1 ->databinding.pager2.currentItem = PagerAdapter.ONE_FRAGMENT_INDEX
                R.id.item_2 ->databinding.pager2.currentItem = PagerAdapter.TWO_FRAGMENT_INDEX
                R.id.item_3 ->databinding.pager2.currentItem = PagerAdapter.THREE_FRAGMENT_INDEX
                R.id.item_4 ->databinding.pager2.currentItem = PagerAdapter.FOUR_FRAGMENT_INDEX
            }
            true
        }

        databinding.pager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    PagerAdapter.ONE_FRAGMENT_INDEX -> databinding.bnv.setSelectedItemId(R.id.item_1)
                    PagerAdapter.TWO_FRAGMENT_INDEX -> databinding.bnv.setSelectedItemId(R.id.item_2)
                    PagerAdapter.THREE_FRAGMENT_INDEX -> databinding.bnv.setSelectedItemId(R.id.item_3)
                    PagerAdapter.FOUR_FRAGMENT_INDEX -> databinding.bnv.setSelectedItemId(R.id.item_4)
                }
            }
        })
    }

}