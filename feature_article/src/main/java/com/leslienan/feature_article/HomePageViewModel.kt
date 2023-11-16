package com.leslienan.feature_article

import androidx.lifecycle.ViewModel
import com.leslienan.core_network.ViewModelExt.simplePager
import com.leslienan.data_article.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    val publishSdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.SIMPLIFIED_CHINESE)

    /**
     * 首页文章列表
     */
    val articleList = simplePager { articleRepository.getArticleList(it) }

}