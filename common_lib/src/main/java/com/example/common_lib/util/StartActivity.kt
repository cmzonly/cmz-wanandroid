package com.example.common_lib.util

import android.content.Context
import android.content.Intent

inline fun <reified T> startActivity(context : Context , block : Intent.() -> Unit){
    val intent = Intent(context , T::class.java)
    block(intent)
    context.startActivity(intent)
}