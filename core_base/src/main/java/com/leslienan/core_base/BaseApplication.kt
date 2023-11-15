package com.leslienan.core_base

import android.app.Application
import com.tencent.mmkv.MMKV

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/14.
 * PS:
 */
open class BaseApplication : Application() {

    companion object {
        lateinit var INSTANCE: BaseApplication
    }


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        MMKV.initialize(this)
    }

}