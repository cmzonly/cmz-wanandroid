package com.example.module_a.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.example.common_lib.base.BaseFragment
import com.example.common_lib.base.BaseStateObserver
import com.example.common_lib.util.Constants
import com.example.common_lib.util.ToastUtil
import com.example.module_a.adapter.ArticleAdapter
import com.example.module_a.bean.Article
import com.example.module_a.bean.Banner
import com.example.module_a.viewmodel.HomeViewModel
import com.example.module_home.R
import com.example.module_home.databinding.ModuleHomeFragmentHomeBinding
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<ModuleHomeFragmentHomeBinding>() {
    private val homeViewModel: HomeViewModel by viewModel()
    private var currentPage = 0
    lateinit var articleAdapter: ArticleAdapter
    private var articleList = arrayListOf<Article.ArticleDetail>()
    private var collectPosition = 0
    override fun init() {

        databinding.ivSearch.setOnClickListener {
            ARouter.getInstance()
                .build(Constants.PATH_SEARCH)
                .navigation()
        }

        databinding.rvArticle.apply {
            articleAdapter = ArticleAdapter(articleList)
            this.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            this.adapter = articleAdapter
            articleAdapter.addChildClickViewIds(R.id.iv_article_collect)

            articleAdapter.setOnItemClickListener { _, _, position ->
                ARouter.getInstance()
                    .build(Constants.PATH_WEB)
                    .withString(Constants.WEB_TITLE, articleList[position].title)
                    .withString(Constants.WEB_LINK, articleList[position].link)
                    .navigation()
            }

            articleAdapter.setOnItemChildClickListener { adapter, view, position ->
                collectPosition = position
                if (articleList[collectPosition].collect) {
                    homeViewModel.uncollect(articleList[collectPosition].id)
                } else {
                    homeViewModel.collect(articleList[collectPosition].id)
                }
            }
        }


        homeViewModel.getBannerList()

        homeViewModel.getArticleList(currentPage)

    }

    override fun observe() {
        homeViewModel.bannerLiveData.observe(this, object : BaseStateObserver<List<Banner>>() {
            override fun getRespDataSuccess(it: List<Banner>) {
                Log.d(TAG, "BaseStateObserver: " + it.size)
                if (it.isEmpty()) {
                    return
                }

                setUi(it)

            }

            override fun getRespDataStart() {
                super.getRespDataStart()
            }
        })

        homeViewModel.articleLiveData.observe(this, object : BaseStateObserver<Article>() {
            @SuppressLint("NotifyDataSetChanged")
            override fun getRespDataSuccess(it: Article) {
                super.getRespDataSuccess(it)
                if (it.datas.isEmpty()) {
                    return
                }
                articleList.addAll(it.datas)
                articleAdapter.notifyDataSetChanged()

            }
        })
        
        homeViewModel.collectLiveDate.observe(this , object : BaseStateObserver<String>(){
            override fun getRespDataStart() {
                showPopup()
            }

            override fun getRespSuccess() {
                dismissPopup()
                if (articleList[collectPosition].collect) {
                    ToastUtil.showMsg("取消收藏！")
                    articleList[collectPosition].collect = false
                } else {
                    ToastUtil.showMsg("收藏成功！")
                    articleList[collectPosition].collect = true
                }
                articleAdapter.notifyItemChanged(collectPosition)
            }

            override fun getRespDataSuccess(it: String) {
                super.getRespDataSuccess(it)
            }

            override fun getRespDataEnd() {
                dismissPopup()
            }
        })
    }

    private fun setUi(bannerList: List<Banner>) {
        databinding.banner.setAdapter(object : BannerImageAdapter<Banner>(bannerList) {
            override fun onBindView(
                holder: BannerImageHolder?,
                data: Banner?,
                position: Int,
                size: Int
            ) {
                holder?.let {
                    Glide.with(requireActivity()).load(data?.imagePath).into(holder.imageView)
                }
            }

        })
    }


    override fun getLayoutId() = R.layout.module_home_fragment_home

    override fun onResume() {
        super.onResume()

    }

}