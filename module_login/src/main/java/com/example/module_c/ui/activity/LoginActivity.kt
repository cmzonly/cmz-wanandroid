package com.example.module_c.ui.activity

import android.content.Intent
import android.text.Editable
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common_lib.base.BaseActivity
import com.example.common_lib.base.BaseStateObserver
import com.example.common_lib.util.Constants
import com.example.common_lib.util.LoginUtil
import com.example.module_c.bean.LoginBean
import com.example.module_c.viewmodel.LoginViewModel
import com.example.module_me.R
import com.example.module_me.databinding.ModuleLoginActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


@Route(path = Constants.PATH_LOGIN)
class LoginActivity : BaseActivity<ModuleLoginActivityLoginBinding>() {


    private val loginViewModel: LoginViewModel by viewModel()

    override fun getLayoutId() = R.layout.module_login_activity_login

    override fun init() {

        /**
         * 注册
         */
        databinding.tvRegister.setOnClickListener {
            ARouter.getInstance()
                .build(Constants.PATH_REGISTER)
                .navigation(this, Constants.CODE_1)


        }

        /**
         * 登录
         */
        databinding.tvLogin.setOnClickListener {
            LoginUtil.login(
                databinding.etAccount.text.toString(),
                databinding.etPassword.text.toString(),
            ) { t1, t2 ->
                loginViewModel.login(t1, t2)
            }

        }


    }

    override fun observe() {
        loginViewModel.loginLiveData.observe(this , object : BaseStateObserver<LoginBean>(){
            override fun getRespDataSuccess(it: LoginBean) {
                finish()
            }
        })
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                Constants.CODE_1 -> {
                    databinding.etAccount.text = Editable.Factory.getInstance()
                        .newEditable(data?.getStringExtra(Constants.USER_NAME))
                    databinding.etPassword.text = Editable.Factory.getInstance()
                        .newEditable(data?.getStringExtra(Constants.PASS_WORD))
                }
            }
        }
    }


}