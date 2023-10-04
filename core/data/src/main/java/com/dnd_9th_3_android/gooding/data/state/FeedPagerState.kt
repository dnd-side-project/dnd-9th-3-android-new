package com.dnd_9th_3_android.gooding.data.state

import androidx.compose.runtime.Stable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

import kotlinx.coroutines.CoroutineScope

@Stable
@OptIn(ExperimentalPagerApi::class)
class FeedPagerState(
    val topFeedPagerState: PagerState, //가로
    val postingFeedPagerState: PagerState, // 세로
    val coroutineScope: CoroutineScope
){}