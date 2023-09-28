package com.dnd_9th_3_android.gooding.feed.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.data.customRowTab.CustomRowTabBar
import com.dnd_9th_3_android.gooding.data.feed.feedPages
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.pretendardMedium
import com.dnd_9th_3_android.gooding.feed.viewModel.FeedOptionViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

//refactoring
// 스크롤 뷰 (now, 추천)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopScrollBarLayer (
    viewModel : FeedOptionViewModel=  hiltViewModel()
) {
    if (viewModel.pagerState!=null && viewModel.applicationState!=null){
        CustomRowTabBar(
            viewModel.pagerState!!.topFeedPagerState,
            viewModel.pagerState!!.coroutineScope,
            feedPages,
            Shadow(
                color = colorResource(id = R.color.tab_shadow),
                blurRadius = 10f
            ),
            pretendardMedium,
            18.sp,
            Color.White,
            colorResource(id = R.color.unselect_white),
            0.dp,
            14.dp,
            indicator = false
        )
    }
}