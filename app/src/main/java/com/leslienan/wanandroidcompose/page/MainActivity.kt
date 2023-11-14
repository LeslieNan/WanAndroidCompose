package com.leslienan.wanandroidcompose.page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leslienan.core_base.ui.theme.toolbarHeight
import com.leslienan.core_base.widget.Toolbar
import com.leslienan.feature_user.page.MePage
import com.leslienan.wanandroidcompose.ui.theme.WanAndroidComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WanAndroidComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val pagerState = rememberPagerState(1)
                    Column(Modifier.fillMaxSize()) {
                        HorizontalPager(
                            pageCount = 4, state = pagerState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .background(Color.Black)
                        ) { page ->
                            when (page) {
                                0 -> {

                                }
                                3->{
                                    MePage()
                                }
                            }
                        }
                        TabRow(
                            selectedTabIndex = 0,
                            Modifier
                                .background(Color.Green)
                                .height(50.dp)
                        ) {

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