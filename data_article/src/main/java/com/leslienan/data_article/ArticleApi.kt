package com.leslienan.data_article

import com.leslienan.core_network.BaseNetModel
import com.leslienan.core_network.ListWrapperModel
import com.leslienan.data_article.model.ArticleModel
import com.leslienan.data_article.model.HomeBannerModel
import com.leslienan.data_article.model.ProjectItemModel
import com.leslienan.data_article.model.ProjectModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
interface ArticleApi {


    @GET("article/list/{pageNum}/json")//获取首页列表
    suspend fun requestArticleList(@Path("pageNum") pageNum: Int): ListWrapperModel<ArticleModel>

    @GET("banner/json")//获取首页banner数据
    suspend fun requestHomeBanner(): List<HomeBannerModel>

    @GET("project/tree/json")//获取项目分类
    suspend fun requestProjectItems(): List<ProjectItemModel>

    @GET("project/list/{pageNum}/json")//获取项目分类
    suspend fun requestProjects(
        @Path("pageNum") pageNum: Int,
        @Query("cid") itemId: String
    ): ListWrapperModel<ProjectModel>


}