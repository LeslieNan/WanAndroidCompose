package com.leslienan.data_article

import com.leslienan.core_network.ListWrapperModel
import com.leslienan.data_article.model.ArticleModel
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
interface ArticleApi {


    @GET("article/list/{pageNum}/json")//主账号信息
    suspend fun requestArticleList(@Path("pageNum") pageNum: Int): ListWrapperModel<ArticleModel>


}