package com.leslienan.wanandroidcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.rememberPermissionState
import com.leslienan.core_base.ui.theme.WanAndroidComposeTheme
import com.leslienan.core_base.ui.theme.backgroundColor
import com.leslienan.feature_article.HomePage
import com.leslienan.feature_user.ui.MePage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WanAndroidComposeTheme() {
                // A surface container using the 'background' color from the theme
                Surface(Modifier.fillMaxSize(), color = backgroundColor) {
                    val tabs = listOf("首页", "知识体系", "成长", "我的")
                    val pagerState = rememberPagerState(0) { 4 }
                    val scope = rememberCoroutineScope()
                    Column(Modifier.fillMaxSize()) {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) { page ->
                            when (page) {
                                0 -> HomePage()
                                3 -> MePage()
                            }
                        }
                        TabRow(
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
                            tabs.forEachIndexed { index, tabName ->
                                val selected = pagerState.currentPage == index
                                Tab(selected = selected,
                                    selectedContentColor = primaryColor,
                                    unselectedContentColor = secondaryColor,
                                    onClick = {
                                        scope.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                    }) {
                                    Text(text = tabName)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    WanAndroidComposeTheme {
//        Column(Modifier.fillMaxSize()) {
//            Toolbar("首页")
//            Spacer(modifier = Modifier.height(10.dp))
//        }
//    }
//}