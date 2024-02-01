package com.leslienan.feature_article

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.paging.compose.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.leslienan.core_base.util.TimeUtil
import kotlinx.coroutines.launch

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/24.
 * PS:
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProjectPage(modifier: Modifier = Modifier.fillMaxSize()) {
    val projectViewModel = viewModel<ProjectViewModel>()
    val tabs by projectViewModel.items.collectAsState()
    val pagerState = rememberPagerState(0) { 4 }
    val scope = rememberCoroutineScope()
    Column(modifier) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            Modifier.height(50.dp),
            indicator = {
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.BottomStart)
                        .width(0.dp)
                        .height(0.dp)//修改指示器高度为1dp，默认2dp
                )
            }
        ) {
            val primaryColor = MaterialTheme.colorScheme.primary
            val secondaryColor = MaterialTheme.colorScheme.secondary
            tabs.forEachIndexed { index, tab ->
                val selected = pagerState.currentPage == index
                Tab(selected = selected,
                    modifier = Modifier.fillMaxHeight(),
                    selectedContentColor = primaryColor,
                    unselectedContentColor = secondaryColor,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }) {
                    Text(text = tab.name, Modifier.fillMaxHeight())
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            if (tabs.isEmpty()) return@HorizontalPager
            val projectList =
                projectViewModel.getProjectList(tabs[page].id).collectAsLazyPagingItems()
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp)) {
                items(projectList, { it.id }) { item ->
                    if (item == null) return@items
                    Row {
                        Image(
                            painter = rememberAsyncImagePainter(item.envelopePic),
                            contentDescription = "",
                            Modifier
                                .width(90.dp)
                                .height(160.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = item.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = item.desc,
                                fontSize = 14.sp,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(Modifier.height(10.dp))
                            Row {
                                Text(item.author, fontSize = 12.sp)
                                Text(TimeUtil.publishSdf.format(item.publishTime), fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }

}