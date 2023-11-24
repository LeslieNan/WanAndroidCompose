package com.leslienan.feature_article

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.leslienan.core_base.widget.Banner

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
@OptIn(ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
fun HomePage(
    modifier: Modifier = Modifier
) {
    val homeViewModel = viewModel<HomePageViewModel>()
    val bannerList by homeViewModel.banner.collectAsState()
    val refreshing by remember { mutableStateOf(false) }
    val articleList = homeViewModel.articleList.collectAsLazyPagingItems()
    val pullRefreshState = rememberPullRefreshState(refreshing, {
        articleList.refresh()
    })
    Box(modifier.pullRefresh(pullRefreshState)) {
        LazyColumn() {
            if (bannerList.isNotEmpty()) {
                item {
                    Banner(bannerList)
                }
            }
            items(items = articleList, key = { it.id }) { item ->
                if (item == null) return@items
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    Modifier
                        .padding(16.dp, 10.dp)
                        .background(Color.White)
                ) {
                    Row {
                        Text(
                            item.author, Modifier
                                .wrapContentHeight()
                                .weight(1f), fontSize = 12.sp
                        )
                        Text(homeViewModel.publishSdf.format(item.publishTime), fontSize = 12.sp)
                    }
                    Text(item.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
        PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}