package com.example.core_basae.widget

import androidx.activity.ComponentActivity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.core_basae.ui.theme.toolbarHeight

@Composable
fun Toolbar(
    title: String = "",
    @DrawableRes rightIcon: Int? = null,
    rightText: String = "",
    onRightClick: (() -> Unit)? = null,
    @DrawableRes backIcon: Int? = null,
    onBackClick: (() -> Unit)? = null,
) {
    Row(Modifier.fillMaxWidth().height(toolbarHeight)) {
        val activity = (LocalContext.current as ComponentActivity)
        IconButton({
            onBackClick?.invoke() ?: activity.onBackPressedDispatcher.onBackPressed()
        }) {
            backIcon?.let {
                Icon(painterResource(it), null)
            } ?: kotlin.run {
                Icon(Icons.Filled.ArrowBack, null)
            }
        }
        Text(title, Modifier.weight(1f, true))
        if (rightText.isNotEmpty()) {
            Text(rightText, Modifier.clickable {
                onRightClick?.invoke()
            })
        }
        rightIcon?.let {
            IconButton({
                onRightClick?.invoke()
            }) {
                Icon(painter = painterResource(it), null)
            }
        }
    }
}