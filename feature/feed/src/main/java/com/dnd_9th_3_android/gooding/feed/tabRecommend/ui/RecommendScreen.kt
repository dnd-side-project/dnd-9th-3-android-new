package com.dnd_9th_3_android.gooding.feed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.data.preventScroll.disabledHorizontalPointerInputScrollPost
import com.dnd_9th_3_android.gooding.data.preventScroll.disabledHorizontalPointerInputScrollPrev
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.feed.recommendFunction.RecommendCard
import com.dnd_9th_3_android.gooding.feed.viewModel.RecommendViewModel

// 메인 피드  - 추천
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecommendScreen(
    recommendViewModel : RecommendViewModel = hiltViewModel()
) {
    HorizontalPager(
        state = rememberPagerState(pageCount = {1}),
        modifier = Modifier
            .fillMaxSize()
            .disabledHorizontalPointerInputScrollPrev()
    ){}


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.blue_gray_7))
    ) {
        
        Spacer(modifier = Modifier.height(119.dp))

        Text(
            text = "${recommendViewModel.currentMonth}월의 추천 굳이데이",
            fontFamily = pretendardBold,
            fontSize = 18.sp,
            modifier = Modifier.padding(start= 18.dp),
            color = Color.White
        )
        
        Spacer(modifier = Modifier.height(24.dp))

        // for 추천 리스트
        val hotState = rememberPagerState(pageCount = {
            recommendViewModel.currentHotCount
        })

        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .height(491.dp)
                .fillMaxWidth()
                .padding(
                    start = 19.dp,
                    end = 19.dp
                )
        ) {
            HorizontalPager(
                state = hotState,
                modifier = Modifier
                    .fillMaxSize()
                    .disabledHorizontalPointerInputScrollPost()
                    .background(Color.Transparent)
            ) { page ->
                RecommendCard()
            }

            // card Indi - 페이지 표시기
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(recommendViewModel.currentHotCount) { iteration ->
                    // iteration color
                    val color =
                        if (hotState.currentPage == iteration) Color.White
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
        Spacer(modifier = Modifier.height(24.dp))

    }
}