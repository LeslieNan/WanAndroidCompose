package com.leslienan.feature_user.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leslienan.feature_user.R

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/14.
 * PS:
 */
@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
fun MePage(
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val userViewModel = viewModel<MePageViewModel>()
    val username by userViewModel.userName.collectAsState()
    ConstraintLayout(modifier) {
        val (imageRef, nameRef, descRef) = createRefs()
        Image(
            painter = painterResource(id = R.mipmap.icon_head_default),
            contentDescription = "头像",
            Modifier
                .constrainAs(imageRef) {
                    top.linkTo(parent.top, 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .width(60.dp)
                .height(60.dp)
        )
        Text(text = username, Modifier.constrainAs(nameRef) {
            top.linkTo(imageRef.bottom, 10.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })


    }
}