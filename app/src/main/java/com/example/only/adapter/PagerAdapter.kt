package com.example.only.adapter

import android.util.SparseArray
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.common_lib.base.BaseFragment
import com.example.module_a.ui.HomeFragment
import com.example.module_web.fragment.TwpFragment
import com.example.module_c.ui.fragment.MineFragment
import com.example.module_d.ui.fragment.FourFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    val fragments = SparseArray<BaseFragment<*>>()

    init {
        fragments.put(ONE_FRAGMENT_INDEX, HomeFragment())
        fragments.put(TWO_FRAGMENT_INDEX, TwpFragment())
        fragments.put(THREE_FRAGMENT_INDEX, MineFragment())
        fragments.put(FOUR_FRAGMENT_INDEX, FourFragment())
    }

    companion object {
        val ONE_FRAGMENT_INDEX = 0
        val TWO_FRAGMENT_INDEX = 1
        val THREE_FRAGMENT_INDEX = 2
        val FOUR_FRAGMENT_INDEX = 3
    }

    override fun getItemCount() = fragments.size()

    override fun createFragment(position: Int) = fragments[position]
}