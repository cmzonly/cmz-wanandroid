package com.example.module_a.viewmodel

import com.example.common_lib.base.BaseLiveData
import com.example.common_lib.base.BaseViewModel
import com.example.module_a.bean.Article
import com.example.module_a.bean.Banner
import com.example.module_a.repo.HomeRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel( val homeRepo: HomeRepo) : BaseViewModel() {
    var bannerLiveData = BaseLiveData<List<Banner>>()

    var articleLiveData = BaseLiveData<Article>()

    val collectLiveDate = BaseLiveData<String>()

     fun getBannerList(){
        launch {
            homeRepo.getBanner(bannerLiveData)
        }
     }

    fun getArticleList(currentPage : Int){
        launch {
            homeRepo.getArticleList(currentPage , articleLiveData)
        }
    }

    //收藏
    fun collect(id : Int){
        launch {
            homeRepo.collect(id ,collectLiveDate )
        }
    }

    //取消收藏
    fun uncollect(id : Int){
        launch {
            homeRepo.uncollect(id , collectLiveDate)
        }
    }


}