package com.example.module_search.ui.activity

import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common_lib.base.BaseActivity
import com.example.common_lib.network.RetrofitManager
import com.example.common_lib.util.Constants
import com.example.common_lib.util.StatusBarUtil
import com.example.module_search.R
import com.example.module_search.api.SearchApi
import com.example.module_search.databinding.ModuleSearchActivitySearchBinding
import com.example.module_search.viewmodel.SearchViewModel
import okhttp3.ResponseBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Route(path = Constants.PATH_SEARCH)
class SearchActivity : BaseActivity<ModuleSearchActivitySearchBinding>() {
    val searchViewModel by viewModel<SearchViewModel>()

    override fun getLayoutId(): Int {
        return R.layout.module_search_activity_search
    }

    override fun init() {
        databinding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })


    }


    override fun observe() {

    }

    override fun initStatusBar(alpha: Float, isDarkFont: Boolean) {
        databinding.statusBar.layoutParams.height = StatusBarUtil.getStatusBarUtil()
    }
}