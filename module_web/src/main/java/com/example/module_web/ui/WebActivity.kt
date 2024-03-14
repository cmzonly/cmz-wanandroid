package com.example.module_web.ui

import android.widget.LinearLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common_lib.base.BaseActivity
import com.example.common_lib.util.Constants
import com.example.common_lib.util.StatusBarUtil
import com.example.module_web.R
import com.example.module_web.databinding.ActivityWebBinding
import com.just.agentweb.AgentWeb

@Route(path = Constants.PATH_WEB)
class WebActivity : BaseActivity<ActivityWebBinding>() {
    override fun getLayoutId() = R.layout.activity_web

    override fun init() {

        databinding.statusBar.layoutParams.height = StatusBarUtil.getStatusBarUtil()

        if (intent == null){
            return
        }

        val title = intent.getStringExtra(Constants.WEB_TITLE)
        databinding.tvWebTitle.text = title
        val link = intent.getStringExtra(Constants.WEB_LINK)
        AgentWeb.with(this)
            .setAgentWebParent(databinding.llWeb, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
            .go(link)

        databinding.ivBackWeb.setOnClickListener {
            finish()
        }
    }
}