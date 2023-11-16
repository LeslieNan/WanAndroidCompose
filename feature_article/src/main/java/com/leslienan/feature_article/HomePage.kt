package com.leslienan.feature_article

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
fun HomePage(
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val homeViewModel = viewModel<HomePageViewModel>()
    val articleList = homeViewModel.articleList.collectAsLazyPagingItems()
    val pullRefreshState = rememberPullRefreshState(refreshing = false, onRefresh = {
        articleList.refresh()
    })
    Box(Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn() {
            articleList.loadState
            items(items = articleList, key = { it.id }) { item ->
                Text(item?.title ?: "")
            }
        }
        PullRefreshIndicator(false, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}