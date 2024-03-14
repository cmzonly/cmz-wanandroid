package com.example.common_lib.util

import android.app.Application
import java.util.regex.Pattern
typealias register = (String , String , String) -> Unit


object LoginUtil {
    const val PHONE_REGEX = "^1[0-9]{10}$"
    const val EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,4}$"
    lateinit var mContext: Application

    fun init(context: Application) {
        mContext = context
    }

    fun login(username: String, password: String, loginBlock :(String, String) -> Unit) {

        if (!isPhone(username) && !isEmail(username)) {
            ToastUtil.showMsg("请输入手机号或邮箱")
            return
        }

        if (password.length !in 6..12) {
            ToastUtil.showMsg("密码最少为6位")
            return
        }

        loginBlock.invoke(username, password)

    }

    fun register(username: String, password: String, passwordRepeat: String , register :register){
        if (!isPhone(username) && !isEmail(username)) {
            ToastUtil.showMsg("请输入手机号或邮箱")
            return
        }

        if (password.length !in 6..12) {
            ToastUtil.showMsg("密码最少为6位")
            return
        }

        if (password != passwordRepeat){
            ToastUtil.showMsg("两次密码不一致")
            return
        }

        register.invoke(username, password , passwordRepeat)
    }


    fun isPhone(phone: String): Boolean {
        val compile = Pattern.compile(PHONE_REGEX)
        val matcher = compile.matcher(phone)
        return matcher.matches()
    }

    fun isEmail(email: String): Boolean {
        val compile = Pattern.compile(EMAIL_REGEX)
        val matcher = compile.matcher(email)
        return matcher.matches()
    }

}