package com.leslienan.wanandroidcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.rememberPermissionState
import com.leslienan.core_base.ui.theme.WanAndroidComposeTheme
import com.leslienan.core_base.ui.theme.backgroundColor
import com.leslienan.feature_article.HomePage
import com.leslienan.feature_article.ProjectPage
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
                    val tabs = listOf("首页", "知识体系", "项目", "成长", "我的")
                    val pagerState = rememberPagerState(0) { tabs.size }
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
                                2 -> ProjectPage()
                                3 -> MePage()
                            }
                        }
                        TabRow(
                            selectedTabIndex = pagerState.currentPage,
                            Modifier
                                .height(50.dp)
                                .background(color = Color.Black),
                            indicator = {
//                                TabRowDefaults.Indicator(
//                                    modifier = Modifier
////                                        .fillMaxWidth()
//                                        .wrapContentSize(Alignment.BottomStart)
//                                        .width(0.dp)
//                                        .height(0.dp)//修改指示器高度为1dp，默认2dp
//                                )
//                                TabRowDefaults.Indicator(
//                                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
//                                )
                                TabRowDefaults.Indicator(height = 0.dp, color = Color.Transparent)
                            }
                        ) {
                            val primaryColor = MaterialTheme.colorScheme.primary
                            val secondaryColor = MaterialTheme.colorScheme.secondary
                            tabs.forEachIndexed { index, tabName ->
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
                                    Text(text = tabName, Modifier.fillMaxHeight())
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