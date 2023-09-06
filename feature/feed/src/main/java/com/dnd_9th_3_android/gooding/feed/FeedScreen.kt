package com.dnd_9th_3_android.gooding.feed

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import com.dnd_9th_3_android.gooding.feed.fixedAreaSubLayout.FeedTopLayout
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

private val pages = listOf("NOW", "HOT")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeedScreen() {
    // 뒤로가기 동작 제어
    BackHandler(enabled = true, onBack = {})
    Box {
        // 페이지 상태 지정 ( now - 추천 가로 페이징 )
        val hoPageState = rememberPagerState()
        HorizontalPager(
            count = pages.size,
            state = hoPageState,
            modifier = Modifier
                .fillMaxSize()
//                .disabledHorizontalPointerInputScroll() //custom
        ) { page ->
                when (page) {
                    0 -> NowScreen()
                else -> RecommendScreen()
            }
        }

        // top menu - 상단 탑 바 (커스텀)
        Box(
            modifier = Modifier.align(Alignment.TopCenter)
        ){
            val coroutineScope = rememberCoroutineScope()
            FeedTopLayout(pageState = hoPageState, coroutineScope = coroutineScope)
        }
    }

}
