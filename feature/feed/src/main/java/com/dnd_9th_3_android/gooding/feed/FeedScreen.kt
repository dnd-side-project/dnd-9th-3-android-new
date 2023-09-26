package com.dnd_9th_3_android.gooding.feed

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.data.feed.feedPages
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.data.state.FeedPagerState

import com.dnd_9th_3_android.gooding.feed.fixedAreaSubLayout.FeedTopLayout
import com.dnd_9th_3_android.gooding.feed.viewModel.FeedOptionViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeedScreen(
    appState : ApplicationState,
    viewModel : FeedOptionViewModel = hiltViewModel()
) {
    // 뒤로가기 동작 제어
    BackHandler(enabled = true, onBack = {})

    // app State 저장
    viewModel.initAppState(appState)
    // pager State 저장
    viewModel.initPagerState(
        FeedPagerState(
            rememberPagerState(),
            rememberPagerState(),
            appState.coroutineScope
        )
    )

    Box {
        viewModel.pagerState?.topFeedPagerState?.let { topPagerState->
            // 페이지 상태 지정 ( now - 추천 가로 페이징 )
            HorizontalPager(
                count = feedPages.size,
                state = topPagerState,
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
            ) {
                FeedTopLayout(
                    goSearch = {
                        viewModel.naviToSearch()
                    }
                )
            }
        }
    }

}
