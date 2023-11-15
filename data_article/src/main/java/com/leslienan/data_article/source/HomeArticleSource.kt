package com.leslienan.data_article.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.leslienan.data_article.ArticleRepository
import com.leslienan.data_article.model.ArticleModel

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
class HomeArticleSource(
    private val articleRepository: ArticleRepository
) : PagingSource<Int, ArticleModel>() {
    override fun getRefreshKey(state: PagingState<Int, ArticleModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleModel> {
        //页码未定义置为1
        val currentPage = params.key ?: 0
        //仓库层请求数据
        Log.d("请求页码标记", "请求第${currentPage}页")
        val articlePage = articleRepository.getArticleList(currentPage)
        //当前页码 小于 总页码 页面加1
        val nextPage = if (currentPage < articlePage.pageCount) {
            currentPage + 1
        } else {
            //没有更多数据
            null
        }

        LoadResult.Page(
            data = articlePage.datas,
            prevKey = null,
            nextKey = nextPage
        )

    }
}