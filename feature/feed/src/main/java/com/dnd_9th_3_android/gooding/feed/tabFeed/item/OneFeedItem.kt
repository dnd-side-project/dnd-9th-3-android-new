package com.dnd_9th_3_android.gooding.feed.itemFeed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.bottomGradient
import com.dnd_9th_3_android.gooding.data.component.topGradient
import com.dnd_9th_3_android.gooding.data.preventScroll.disabledHorizontalPointerInputScrollPost
import com.dnd_9th_3_android.gooding.feed.feedScreen.OneFeedUriContent
import com.dnd_9th_3_android.gooding.feed.itemFeed.midInfoFunction.GradientBoxState
import com.dnd_9th_3_android.gooding.feed.itemFeed.midInfoFunction.PaddingState
import com.dnd_9th_3_android.gooding.feed.viewModel.MainFeedViewModel
import com.dnd_9th_3_android.gooding.model.feed.GetMainFeed

// refactoring
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OneFeedItem(
    feedViewModel: MainFeedViewModel = hiltViewModel(),
    feed: GetMainFeed
) {
    // 현재 피드 셀렉
    feedViewModel.setCurrentFeed(feed)

    // 텍스트 화면 확장 상태
    var extendState by remember { mutableStateOf(1) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        // poster paging
        val pagerState = rememberPagerState(pageCount = { feed.files.size })

        // feed image list
        HorizontalPager(
            state = pagerState,
            modifier =
            if (pagerState.currentPage == feed.files.size - 1) Modifier.disabledHorizontalPointerInputScrollPost()
            else Modifier
        ) { page ->
            OneFeedUriContent(feed.files[page].fileUrl)
        }

        // top Box - add shadow
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(136.dp)
                .align(Alignment.TopCenter)
                .background(
                    brush = topGradient()
                )
        )
        // bottom Box - add shadow
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(GradientBoxState(extendState))
                .align(Alignment.BottomCenter)
                .background(
                    brush = bottomGradient()
                )
        )

        // top layer
        Column(
            Modifier.align(Alignment.TopCenter)
        ) {
            Spacer(modifier = Modifier.height(95.dp))
            UserInfoLayer(userInfo = feed.user)
        }
        //bottom layer
        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .height(GradientBoxState(extendState))
        ) {
            Spacer(modifier = Modifier.height(PaddingState(extendState)))
            ContentInfoLayer(
                feed.placeTitle,
                feed.title,
                feed.description,
                extendState,
                changeState = {
                    extendState = it
                }
            )
        }

        // Image Indi - 페이지 표시기
        if (feed.files.size > 1) {
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(bottom = 122.dp)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(feed.files.size) { iteration ->
                    // iteration color
                    val color =
                        if (pagerState.currentPage == iteration) Color.White
                        else colorResource(id = R.color.feed_in_di_back)
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(6.dp)
                    )
                }
            }
        }
    }
}

