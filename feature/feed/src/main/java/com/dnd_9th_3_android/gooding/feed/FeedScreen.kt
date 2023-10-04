package com.dnd_9th_3_android.gooding.feed

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.data.feed.feedPages
import com.dnd_9th_3_android.gooding.data.preventScroll.disabledHorizontalPointerInputScrollPost
import com.dnd_9th_3_android.gooding.data.preventScroll.disabledHorizontalPointerInputScrollPrev
import com.dnd_9th_3_android.gooding.data.preventScroll.disabledVerticalPointerInputScrollPost
import com.dnd_9th_3_android.gooding.data.preventScroll.disabledVerticalPointerInputScrollPrev
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.data.state.FeedPagerState

import com.dnd_9th_3_android.gooding.feed.topBar.ui.FeedTopLayout
import com.dnd_9th_3_android.gooding.feed.viewModel.FeedOptionViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.state.rememberFeedPagerState

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun FeedScreen(
    appState : ApplicationState,
    viewModel : FeedOptionViewModel = hiltViewModel()
) {
    // 뒤로가기 동작 제어
    BackHandler(enabled = true, onBack = {})

    // app State 저장
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    viewModel.initAppState(appState,screenWidth)
    // pager State 저장
    viewModel.initPagerState(rememberFeedPagerState())

    Box (
        modifier = Modifier
            .background(colorResource(id = R.color.blue_gray_7))
    ){
        viewModel.pagerState?.topFeedPagerState?.let { topPagerState->

            // 페이지 상태 지정 ( now - 추천 가로 페이징 )
            HorizontalPager(
                count = feedPages.size,
                state = topPagerState,
                modifier = Modifier
                    .fillMaxSize()
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
