package com.dnd_9th_3_android.gooding.login

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.dnd_9th_3_android.gooding.core.data.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){

        // 뒤로가기 제어
        BackHandler(enabled = true, onBack = {})

        // iamge box
        Image(
            painter = painterResource(id = R.drawable.gooding_splash_image),
            contentDescription = null,
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.padding_101),
                    top = dimensionResource(id = R.dimen.padding_264)
                )
                .width(dimensionResource(id = R.dimen.spl_image_w))
                .height(dimensionResource(id = R.dimen.spl_image_h))
        )

        // text image
        Image(
            painterResource(id = R.drawable.gooding_splash_text),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    bottom = dimensionResource(id = R.dimen.padding_78)
                )
                .width(dimensionResource(id = R.dimen.size_135))
                .height(dimensionResource(id = R.dimen.size_37))
        )

        val coroutineScope = rememberCoroutineScope()
        coroutineScope.launch {
            delay(1000)
            navController.navigate("sosScreen")
        }
    }
}