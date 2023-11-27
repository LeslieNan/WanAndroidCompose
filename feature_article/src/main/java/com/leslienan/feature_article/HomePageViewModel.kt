package com.leslienan.feature_article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leslienan.core_base.widget.BannerData
import com.leslienan.core_network.ViewModelExt.exceptionHandler
import com.leslienan.core_network.ViewModelExt.simplePager
import com.leslienan.data_article.ArticleRepository
import com.leslienan.data_article.model.HomeBannerModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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

    private val _banner = MutableStateFlow<List<BannerData>>(listOf())
    val banner = _banner.asStateFlow()

    init {
        getBannerData()
    }

    /**
     * 首页文章列表
     */
    val articleList = simplePager { articleRepository.getArticleList(it) }

    fun getBannerData() {
        viewModelScope.launch(exceptionHandler) {
            val homeBanner = articleRepository.getHomeBanner()
            val bannerList = mutableListOf<BannerData>()
            homeBanner.forEach {
                bannerList.add(BannerData(it.imagePath, it.url))
            }
            _banner.emit(bannerList)
        }
    }

}