package com.leslienan.feature_article

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
fun HomePage(
    modifier: Modifier = Modifier.fillMaxSize()
){
    val homeViewModel = viewModel<HomePageViewModel>()
    

}