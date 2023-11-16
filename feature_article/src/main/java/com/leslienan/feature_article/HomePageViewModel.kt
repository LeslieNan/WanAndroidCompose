package com.leslienan.feature_article

import androidx.lifecycle.ViewModel
import com.leslienan.core_network.ViewModelExt.simplePager
import com.leslienan.data_article.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    val articleList = simplePager { articleRepository.getArticleList(it) }

}