package com.example.common_lib.util

class Constants {
     companion object{
         const val USER_NAME = "user_name"
         const val PASS_WORD = "password"
         const val HTTP_SUCCESS = 0  //代表执行成功
         const val HTTP_NO_LOGIN = -1001  //代表登录失效，需要重新登录
         const val DEFAULT_PAGER_SIZE = 10
         const val WEB_TITLE = "web_title"
         const val WEB_LINK = "web_link"
         const val USER_COOKIE = "user_cookie"

         //路由路径
         const val PATH_WEB = "/module_web/ui/WebActivity"
         const val PATH_LOGIN = "/module_login/ui/LoginActivity"
         const val PATH_REGISTER = "/module_login/ui/RegisterActivit"
         const val PATH_SEARCH = "/module_search/ui/SearchActivity"


         //requestCode
         const val CODE_1 = 1

     }
}