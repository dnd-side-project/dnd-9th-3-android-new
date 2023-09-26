package com.dnd_9th_3_android.gooding.feed.mainScreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.dnd_9th_3_android.gooding.feed.fixedAreaSubLayout.FeedTopLayout
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer

import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.feed.itemFeed.RomanticBarLayer
import com.dnd_9th_3_android.gooding.feed.viewModel.MainFeedViewModel
//refactoring
// main 고정 화면
// Fixed는 Box를 사용하므로, 자체 패딩 꼭 추가 !!
@OptIn(ExperimentalPagerApi::class)
@Composable
fun FixedAreaScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ){

        // book mark
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    bottom = 197.dp,
                    end = 16.dp
                )
                .size(24.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.bookmark),
                contentDescription =null ,
                modifier = Modifier.fillMaxSize()
            )
        }

        // romantic per
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {

            RomanticBarLayer(0.0f)

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}