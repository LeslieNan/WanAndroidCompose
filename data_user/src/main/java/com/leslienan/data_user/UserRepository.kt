package com.leslienan.data_user

import android.content.Context
import com.leslienan.core_network.createRequestBody

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
class UserRepository(private val context: Context, private val userApi: UserApi) {


    suspend fun login(username: String, password: String) {
        userApi.login(createRequestBody {
            put("username", username)
            put("password", password)
        })
    }
}