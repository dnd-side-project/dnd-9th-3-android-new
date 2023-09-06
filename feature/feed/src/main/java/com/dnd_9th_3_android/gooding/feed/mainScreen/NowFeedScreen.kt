package com.dnd_9th_3_android.gooding.feed

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dnd_9th_3_android.gooding.feed.feedScreen.TabFeedPager
import com.dnd_9th_3_android.gooding.feed.mainScreen.FixedAreaScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

// 메인 피드 - now
@OptIn(ExperimentalPagerApi::class)
@Composable
fun NowScreen() {

    val verPageState = rememberPagerState() // current feed state -> ver

    Box(modifier = Modifier.fillMaxSize()){
        // 피드 페이저 - 위아래 스크롤 뷰 ver
        TabFeedPager(verPageState)
        // 고정 화면
        FixedAreaScreen(verPageState)
    }
}