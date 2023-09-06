package com.dnd_9th_3_android.gooding.feed.fixedAreaSubLayout

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.feed.layout.TopScrollBarLayer
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope

// 스크롤 뷰를 퐇마한 상단 데이터
@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeedTopLayout(
    pageState: PagerState,
    coroutineScope: CoroutineScope,
) {
    // top Layout
    Box(
        Modifier
            .padding(
                start = dimensionResource(id = R.dimen.padding_18),
                end = dimensionResource(id = R.dimen.padding_16),
                top = dimensionResource(id = R.dimen.padding_55),
                bottom = dimensionResource(id = R.dimen.padding_13)
            )
    ) {
        // top bar .. !
        Box(
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            TopScrollBarLayer(pageState, coroutineScope )
        }


        // search function
        var goSearchActivity by remember {
            mutableStateOf(false)
        }
        if (goSearchActivity){
            // go search activity
            val intent = Intent(
                LocalContext.current.applicationContext,
                Class.forName("com.dnd_9th_3_android.gooding.presentation.search_feed.SearchFeedListActivity")
            )
            LocalContext.current.startActivity(intent)
            goSearchActivity = false
        }

        // search button
        Box(
            Modifier
                .align(Alignment.TopEnd)
                .size(dimensionResource(id = R.dimen.padding_24))
                .clickable {
                    goSearchActivity = true
                }
        ){
            Image(
                painterResource(id = R.drawable.search),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}