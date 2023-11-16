package com.leslienan.core_base

import android.app.Application
import com.elvishew.xlog.LogConfiguration
import com.elvishew.xlog.LogLevel
import com.elvishew.xlog.XLog
import com.elvishew.xlog.interceptor.BlacklistTagsFilterInterceptor
import com.example.core_base.BuildConfig
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
        initLog()
    }

    private fun initLog() {
        val config = LogConfiguration.Builder()
            .logLevel(
                if (BuildConfig.DEBUG) LogLevel.ALL // Specify log level, logs below this level won't be printed, default: LogLevel.ALL
                else LogLevel.NONE
            )
            .tag("WanAndroidTag") // Specify TAG, default: "X-LOG"
            .enableThreadInfo() // Enable thread info, disabled by default
            .enableStackTrace(2) // Enable stack trace info with depth 2, disabled by default
            .enableBorder() // Enable border, disabled by default
//            .addInterceptor(
//                BlacklistTagsFilterInterceptor( // Add blacklist tags filter
//                    "blacklist1", "blacklist2", "blacklist3"
//                )
//            )
            .build()
        XLog.init(config)
    }

}