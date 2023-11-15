package com.leslienan.data_user

import com.leslienan.data_user.model.UserModel
import com.leslienan.core_network.NetConst
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
interface UserApi {

    @POST(NetConst.baseUrl + "/user/login")//主账号信息
    suspend fun login(@Body requestBody: RequestBody): UserModel
}