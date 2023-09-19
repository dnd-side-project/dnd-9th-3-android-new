package com.dnd_9th_3_android.gooding.feed.feedScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.feed.itemFeed.OneFeedItem
import com.dnd_9th_3_android.gooding.feed.viewModel.MainFeedViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.VerticalPager

//피드  데이터에 대한 뷰모델 생성
// 각 하나의 피드에 대한 뷰 페이저
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabFeedPager(
    verPageState : PagerState,
    feedViewModel: MainFeedViewModel = hiltViewModel()
) {
    // sample code : init view model
    feedViewModel.initFeedData()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        VerticalPager(
            count = feedViewModel.feedList.size,
            state = verPageState
        ) {page->
            OneFeedItem(feed = feedViewModel.feedList[page])
        }
    }

}