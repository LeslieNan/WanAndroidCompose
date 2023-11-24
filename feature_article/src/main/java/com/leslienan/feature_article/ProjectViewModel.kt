package com.leslienan.feature_article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.leslienan.core_base.widget.BannerData
import com.leslienan.core_network.ViewModelExt.exceptionHandler
import com.leslienan.core_network.ViewModelExt.simplePager
import com.leslienan.data_article.ArticleRepository
import com.leslienan.data_article.model.ProjectItemModel
import com.leslienan.data_article.model.ProjectModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/24.
 * PS:
 */
@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _items = MutableStateFlow<List<ProjectItemModel>>(listOf())
    val items = _items.asStateFlow()

    private val pageMap = mutableMapOf<String, Flow<PagingData<ProjectModel>>>()

    init {
        getProjectItemList()
    }

    fun getProjectItemList() {
        viewModelScope.launch(exceptionHandler) {
            _items.emit(articleRepository.getProjectItems())
        }
    }

    fun getProjectList(itemId: String): Flow<PagingData<ProjectModel>> {
        if (pageMap[itemId] == null) pageMap[itemId] = simplePager { articleRepository.getProjectList(itemId, it) }
        return pageMap[itemId]!!
    }


}