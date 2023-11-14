package com.leslienan.wanandroidcompose.page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.leslienan.core_base.ui.theme.toolbarHeight
import com.leslienan.core_base.widget.Toolbar
import com.leslienan.wanandroidcompose.ui.theme.WanAndroidComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WanAndroidComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(Modifier.fillMaxSize()) {
                        Toolbar("首页")
                        Spacer(modifier = Modifier.height(toolbarHeight))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WanAndroidComposeTheme {
//        val pagerState = rememberPagerState(initialPage = 0)
//        val scope = rememberCoroutineScope()
//        val currentIndex = pagerState.currentPage
//        LaunchedEffect(currentIndex) {
//            launch(Dispatchers.IO) {
////                AppSystemSetManage.homeTabRememberPage = currentIndex
//            }
//        }


    }
}