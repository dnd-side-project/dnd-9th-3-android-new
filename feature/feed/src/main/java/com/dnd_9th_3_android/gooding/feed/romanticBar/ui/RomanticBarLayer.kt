package com.dnd_9th_3_android.gooding.feed.itemFeed

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.coustomShadow
import com.dnd_9th_3_android.gooding.feed.romanticBar.BarInternalContent
import com.dnd_9th_3_android.gooding.feed.romanticBar.ProgressGradient
import com.dnd_9th_3_android.gooding.feed.romanticBar.function.ProgressGraphic
import com.dnd_9th_3_android.gooding.feed.romanticBar.ProgressSlider
import com.dnd_9th_3_android.gooding.feed.viewModel.FeedOptionViewModel
import com.dnd_9th_3_android.gooding.feed.viewModel.MainFeedViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RomanticBarLayer(
    initBarData : Float,
    optionalViewModel : FeedOptionViewModel = hiltViewModel(),
    feedViewModel : MainFeedViewModel = hiltViewModel()
) {
    // 현재 진행 상태
    val currentValue = remember {
        mutableStateOf(initBarData)
    }
    // progress animation
    val progressAnimDuration = 500
    val progressAnimation by animateFloatAsState(
        targetValue = currentValue.value,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing)
    )
    val currentOffset = remember {
        mutableStateOf(Offset(0f,0f))
    }

//     페이지 변화 감지
    optionalViewModel.pagerState?.let{ feedPager->
        LaunchedEffect(feedPager.postingFeedPagerState){
            snapshotFlow { feedPager.postingFeedPagerState.currentPage }.collect { page->
                feedViewModel.setCurrentFeed(feedViewModel.feedList[page])
                feedViewModel.currentFeed.value.let{
                    currentValue.value = it?.recordScore?.toFloat() ?: 0.0f
                }
            }
        }

    }
    val widthRatio = optionalViewModel.screenWidth / 360
    // total 높이 : 85(70+15) 드래그 탑 아이콘 까지 고려
    Box(
        modifier = Modifier
            // 324
            .width(widthRatio*324)
            .height(85.dp)
    ){
        // custom box (배경) (____)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .align(Alignment.BottomCenter)
                .background(
                    color = colorResource(id = R.color.romantic_bar_color),
                    shape = RoundedCornerShape(16.dp)
                )
                .coustomShadow(
                    color = colorResource(id = R.color.shadow_color_romantic),
                    offsetY =4.dp,
                    blurRadius = 30f
                )
                .blur(60.dp)

        )
        // 배경 위 메인 뷰
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .align(Alignment.BottomCenter)
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ){
            BarInternalContent(widthRatio) // 배경 내부 뷰

            // 그라데이션 뷰
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        top = 31.dp,
                        bottom = 31.dp
                    ),
                contentAlignment = Alignment.Center
            ){
                ProgressGradient(progressAnimation,widthRatio)
            }

            // 투명 스크롤러 - 드래그 관리 !
            Box(
                modifier = Modifier
                    .padding(
                        start = 40.dp,
                        end = 40.dp,
                        top = 13.dp,
                        bottom = 13.dp
                    )
                    .width(widthRatio*244)
                    .height(44.dp)
                    .background(Color.Transparent)
            ){
                ProgressSlider(
                    currentValue = currentValue.value,
                    changeValue = { value, offset ->
                        currentValue.value = value
                        currentOffset.value = offset
                        feedViewModel.setRomantic(value.toInt())
                    }
                )
            }
        }

        //현재 상태 아이콘
        ProgressGraphic(progress = progressAnimation, widthRatio = widthRatio)
    }
}