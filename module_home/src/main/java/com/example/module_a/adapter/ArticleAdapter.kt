package com.example.module_a.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.module_a.bean.Article
import com.example.module_home.R

class ArticleAdapter(dataList: MutableList<Article.ArticleDetail>) :
    BaseQuickAdapter<Article.ArticleDetail, BaseViewHolder>(R.layout.module_home_item_article, dataList) {

    override fun convert(holder: BaseViewHolder, item: Article.ArticleDetail) {
        if (holder.adapterPosition < 2){
            holder.setText(R.id.tv_article_type , "New")
            holder.setVisible(R.id.tv_article_type , true)
        }else{
            holder.setGone(R.id.tv_article_type , true)
        }
        holder.setText(R.id.tv_article_author , item.shareUser)
        holder.setText(R.id.tv_article_share_date , item.niceShareDate)
        holder.setText(R.id.tv_article_content , item.title)
        holder.setText(R.id.tv_article_sort , "分类：${item.superChapterName}")

        if (item.collect){
            holder.setImageResource(R.id.iv_article_collect , R.drawable.collect_s)
        }else{
            holder.setImageResource(R.id.iv_article_collect , R.drawable.collect_n)
        }
    }
}