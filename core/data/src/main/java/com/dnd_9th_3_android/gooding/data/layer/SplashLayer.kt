package com.dnd_9th_3_android.gooding.data

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dnd_9th_3_android.gooding.core.data.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashLayer(
) {
    // 뒤로가기 동작 제어
    BackHandler(enabled = true, onBack = {})

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.blue_gray_7))
    ){
        // iamge box
        Image(
            painter = painterResource(id = R.drawable.gooding_splash_image),
            contentDescription = null,
            modifier = Modifier
                .padding(
                    start = 0.62.dp,
                    bottom = 49.6.dp
                )
                .width(158.62.dp)
                .height(177.4.dp)
                .align(Alignment.Center)
        )

        // text image
        Image(
            painterResource(id = R.drawable.gooding_splash_text),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    bottom = 78.dp
                )
                .width(135.dp)
                .height(37.dp)
        )
    }
}