package com.dnd_9th_3_android.gooding.data.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import com.google.accompanist.pager.rememberPagerState

@OptIn( ExperimentalPagerApi::class)
@Composable
fun rememberFeedPagerState(
    topFeedPagerState: PagerState = rememberPagerState(),
    postingFeedPagerState: PagerState = rememberPagerState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
    )=remember(topFeedPagerState,postingFeedPagerState,coroutineScope) {
    FeedPagerState(
        topFeedPagerState,
        postingFeedPagerState,
        coroutineScope
    )
}