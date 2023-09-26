package com.dnd_9th_3_android.gooding.feed.fixedAreaSubLayout

import android.content.Intent
import androidx.compose.foundation.Image
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
            .padding(
                start = 18.dp,
                end = 16.dp,
                top = 55.dp,
                bottom = 13.dp
            )
    ) {
        // top bar .. ! -> left
        Box(
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            TopScrollBarLayer()
        }


//        // search function
//        var goSearchActivity by remember {
//            mutableStateOf(false)
//        }
//        if (goSearchActivity){
//            // go search activity
//            val intent = Intent(
//                LocalContext.current.applicationContext,
//                Class.forName("com.dnd_9th_3_android.gooding.presentation.search_feed.SearchFeedListActivity")
//            )
//            LocalContext.current.startActivity(intent)
//            goSearchActivity = false
//        }

        // search button -> right
        Box(
            Modifier
                .align(Alignment.TopEnd)
                .size(24.dp)
                .clickable {
                    goSearch()
                }
        ){
            Image(
                painterResource(id = R.drawable.search_24),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}