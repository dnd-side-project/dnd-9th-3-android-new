package com.dnd_9th_3_android.gooding.feed.tabFeed.function

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.dnd_9th_3_android.gooding.feed.itemFeed.OneFeedItem
import com.dnd_9th_3_android.gooding.feed.viewModel.FeedOptionViewModel
import com.dnd_9th_3_android.gooding.feed.viewModel.MainFeedViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager

// refactoring
//피드  데이터에 대한 뷰모델 생성
// 각 하나의 피드에 대한 뷰 페이저
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabFeedPager(
    optionViewModel : FeedOptionViewModel = hiltViewModel(),
    feedViewModel: MainFeedViewModel = hiltViewModel()
) {
    val lazyPagingItems = feedViewModel.feedDataList.collectAsLazyPagingItems()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        optionViewModel.pagerState?.let { feedPager ->
            VerticalPager(
                count = lazyPagingItems.itemCount,
                state = feedPager.postingFeedPagerState
            ) { page ->
                OneFeedItem(feed = lazyPagingItems[page]!!)
            }
        }
    }

}