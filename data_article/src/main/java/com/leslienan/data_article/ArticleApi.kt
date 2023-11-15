package com.leslienan.data_article

import com.leslienan.core_network.NetConst
import com.leslienan.data_article.model.ArticlePageModel
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
interface ArticleApi {


    @GET(NetConst.baseUrl + "/article/list/{pageNum}}/json")//主账号信息
    suspend fun requestArticleList(@Path("pageNum") pageNum: Int): ArticlePageModel


}