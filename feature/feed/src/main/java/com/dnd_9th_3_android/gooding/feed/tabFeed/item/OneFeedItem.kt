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
import com.dnd_9th_3_android.gooding.api.feedApi.entity.MainFeedEntity
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.bottomGradient
import com.dnd_9th_3_android.gooding.data.component.topGradient
import com.dnd_9th_3_android.gooding.data.preventScroll.disabledHorizontalPointerInputScrollPost
import com.dnd_9th_3_android.gooding.data.preventScroll.disabledHorizontalPointerInputScrollPrev
import com.dnd_9th_3_android.gooding.feed.feedScreen.OneFeedUriContent
import com.dnd_9th_3_android.gooding.feed.itemFeed.midInfoFunction.GradientBoxState
import com.dnd_9th_3_android.gooding.feed.viewModel.MainFeedViewModel
import com.dnd_9th_3_android.gooding.model.feed.model.MainFeed

// refactoring
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OneFeedItem(
    feedViewModel: MainFeedViewModel = hiltViewModel(),
    feed: MainFeedEntity
) {
    // 현재 피드 셀렉
    feedViewModel.setCurrentFeed(feed)

    // 텍스트 화면 확장 상태
    var extendState by remember { mutableStateOf(1) }
    // poster paging
    val pagerState = rememberPagerState(pageCount = { feed.files.size })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        // feed image list
        HorizontalPager(
            state = pagerState,
            modifier =  Modifier.disabledHorizontalPointerInputScrollPrev()
        ) { page ->
            OneFeedUriContent(feed.files[page].fileUrl)
        }

        // top Box - add shadow
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(136.dp)
                .align(Alignment.TopCenter)
                .background(
                    brush = topGradient()
                )
        ){
            Spacer(modifier = Modifier.height(95.dp))
            UserInfoLayer(userInfo = feed.user)
        }

        // bottom Box - add shadow
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(GradientBoxState(extendState))
                .align(Alignment.BottomCenter)
                .background(
                    brush = bottomGradient()
                ),
            contentAlignment = Alignment.BottomCenter
        ){
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

    //prevent scroll
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .disabledHorizontalPointerInputScrollPost()
    ){}
}

