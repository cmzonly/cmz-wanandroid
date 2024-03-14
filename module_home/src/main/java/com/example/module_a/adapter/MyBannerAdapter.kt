package com.example.module_a.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.module_a.bean.Banner
import com.example.module_home.R

class MyBannerAdapter(data: MutableList<Banner>) : BaseQuickAdapter<Banner , BaseViewHolder>(R.layout.module_home_item_banner , data){
    override fun convert(holder: BaseViewHolder, item: Banner) {
        Glide.with(context).load(item.imagePath).into(holder.getView(R.id.iv_banner))
    }

}