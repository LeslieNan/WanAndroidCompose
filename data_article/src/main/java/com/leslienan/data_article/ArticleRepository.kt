package com.leslienan.data_article

import android.content.Context

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
class ArticleRepository(private val context: Context, private val articleApi: ArticleApi) {

    /**
     * 获取文章列表
     */
    suspend fun getArticleList(pageNum: Int) = articleApi.requestArticleList(pageNum)

    /**
     * 获取首页banner数据
     */
    suspend fun getHomeBanner() = articleApi.requestHomeBanner()

}