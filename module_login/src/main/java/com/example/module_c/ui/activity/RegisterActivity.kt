package com.example.module_c.ui.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common_lib.base.BaseActivity
import com.example.common_lib.base.BaseStateObserver
import com.example.common_lib.util.Constants
import com.example.common_lib.util.LoginUtil
import com.example.module_c.bean.RegisterBean
import com.example.module_c.viewmodel.LoginViewModel
import com.example.module_me.R
import com.example.module_me.databinding.ModuleLoginActivityRegisterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


@Route(path = Constants.PATH_REGISTER)
class RegisterActivity : BaseActivity<ModuleLoginActivityRegisterBinding>() {
    private val loginViewModel: LoginViewModel by viewModel()
    override fun getLayoutId() = R.layout.module_login_activity_register

    override fun init() {
        databinding.tvRegister.setOnClickListener {
            LoginUtil.register(
                databinding.etAccount.text.toString(),
                databinding.etPassword.text.toString(),
                databinding.etPasswordRepeat.text.toString()
            ) {s1 , s2 , s3 ->
                loginViewModel.register(s1 , s2 ,s3)
            }
        }
    }

    override fun observe() {
        loginViewModel.registerLiveData.observe(this , object  : BaseStateObserver<RegisterBean>(){

            override fun getRespDataStart() {
                showPopup()
            }

            override fun getRespDataSuccess(it: RegisterBean) {
                intent.apply {
                    intent.putExtra(Constants.USER_NAME , it.username)
                    intent.putExtra(Constants.PASS_WORD ,databinding.etPassword.text.toString())
                    setResult(RESULT_OK , intent)
                    dismissPopup()
                    finish()
                }

            }

            override fun getRespDataEnd() {

            }

        })
    }


}