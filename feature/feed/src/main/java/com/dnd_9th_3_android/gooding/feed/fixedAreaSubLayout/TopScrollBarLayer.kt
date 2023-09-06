package com.dnd_9th_3_android.gooding.feed.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dnd_9th_3_android.gooding.data.contentLayout.CustomRowTabBar
import com.dnd_9th_3_android.gooding.data.feed.feedPages
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.contentLayout.camptonMedium
import com.dnd_9th_3_android.gooding.data.contentLayout.pretendardBold
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope

// 스크롤 뷰 (now, 추천)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopScrollBarLayer (
    pageState: PagerState,
    coroutineScope: CoroutineScope,
) {
    CustomRowTabBar(
        pageState,
        coroutineScope,
        feedPages,
        Shadow(
            color = colorResource(id = R.color.tab_shadow),
            blurRadius = 10f
        ),
        pretendardBold,
        18.sp,
        Color.White,
        colorResource(id = R.color.unselect_white),
        0.dp,
        dimensionResource(id = R.dimen.padding_14),
        indicator = false
    )
}