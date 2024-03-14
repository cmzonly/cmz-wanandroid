package com.example.common_lib.util

import com.tencent.mmkv.MMKV

object MMKVUtil {

    /**
     * 获取mmkv实例的几种方式
     *
     *  // 获取 MMKV 默认全局实例，一般选用这个，本文也是选择这个进行实例创建
     *     val mmkv = MMKV.defaultMMKV()
     *
     *     // 根据设置 id 来自定义 MMKV 对象。比如根据业务来区分的存取实例
     *     val mmkv = MMKV.mmkvWithID("ID")
     *
     *     // 开启多进程访问。默认是单线程
     *     val mmkv = MMKV.mmkvWithID("ID",MMKV.MULTI_PROCESS_MODE)
     *
     */
    val kv = MMKV.defaultMMKV()

    fun put(key : String , value : Any){
        when(value){
            is Int -> kv.putInt(key , value)
            is Float -> kv.putFloat(key , value)
            is Boolean -> kv.putBoolean(key , value)
            is Long -> kv.putLong(key , value)
            is ByteArray -> kv.putBytes(key , value)
            is String -> kv.putString(key , value)
        }
    }

    fun getBoolean(key: String, defValue: Boolean = false) = kv.getBoolean(key, defValue)

    fun getBytes(key: String, defValue: ByteArray? = null) = kv.getBytes(key, defValue)

    fun getFloat(key: String, defValue: Float = 0f) = kv.getFloat(key, defValue)

    fun getInt(key: String, defValue: Int = 0) = kv.getInt(key, defValue)

    fun getLong(key: String, defValue: Long = 0L) = kv.getLong(key, defValue)

    fun getString(key: String, defValue: String? = null) = kv.getString(key, defValue)

    fun remove(key: String) = kv.remove(key)


}