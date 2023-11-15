package com.leslienan.data_user

import com.tencent.mmkv.MMKV

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
object UserManager {
    private val mmkv = MMKV.mmkvWithID("user")!!

    var username: String
        get() = mmkv.getString("username", "") ?: ""
        set(value) {
            mmkv.putString("username", value)
        }

}