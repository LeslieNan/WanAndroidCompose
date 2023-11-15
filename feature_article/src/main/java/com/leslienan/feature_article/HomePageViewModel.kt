package com.leslienan.feature_article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leslienan.core_network.ViewModelExt.exceptionHandler
import com.leslienan.data_article.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    private var pageNum = 0


    init {
        viewModelScope.launch(exceptionHandler) {
            articleRepository.getArticleList(pageNum)
        }
    }

}