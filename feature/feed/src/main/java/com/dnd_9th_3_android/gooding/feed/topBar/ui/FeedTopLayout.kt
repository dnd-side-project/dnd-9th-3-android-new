package com.dnd_9th_3_android.gooding.feed.topBar.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.feed.layout.TopScrollBarLayer
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope

//refactoring
// 스크롤 뷰를 포함한 상단 데이터
@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeedTopLayout(
    goSearch : ()-> Unit
) {
    // top Layout
    Box(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = 18.dp,
                end = 16.dp,
                top = 55.dp,
                bottom = 13.dp
            )
    ) {
        // top bar .. ! -> left
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .fillMaxWidth(),
        ) {
            TopScrollBarLayer()
        }


        // search button -> right
        Box(
            Modifier
                .align(Alignment.CenterEnd)
                .size(24.dp)
                .clickable {
                    goSearch()
                },
            contentAlignment = Alignment.Center
        ){
            Image(
                painterResource(id = R.drawable.search_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}