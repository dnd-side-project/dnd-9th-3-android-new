package com.dnd_9th_3_android.gooding.feed.itemFeed

import android.util.Log
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer

import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.contentLayout.coustomShadow
import com.dnd_9th_3_android.gooding.feed.romanticFunction.BarInternalContent
import com.dnd_9th_3_android.gooding.feed.romanticFunction.ProgressGradient
import com.dnd_9th_3_android.gooding.feed.romanticFunction.ProgressGraphic
import com.dnd_9th_3_android.gooding.feed.romanticFunction.ProgressSlider
import com.dnd_9th_3_android.gooding.feed.viewModel.MainFeedViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RomanticBarLayer(
    initBarData : Float,
    verPageState : PagerState,
    feedViewModel : MainFeedViewModel = hiltViewModel()
) {
    // 현재 진행 상태
    val currentValue = remember {
        mutableStateOf(initBarData)
    }
    // progress animation
    val progressAnimDuration = 700
    val progressAnimation by animateFloatAsState(
        targetValue = currentValue.value,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing)
    )
    val currentOffset = remember {
        mutableStateOf(Offset(0f,0f))
    }

//     페이지 변화 감지
    LaunchedEffect(verPageState){
        snapshotFlow { verPageState.currentPage }.collect { page->
            feedViewModel.setCurrentFeed(feedViewModel.feedList[page])
            feedViewModel.currentFeed.value.let{
                currentValue.value = it?.recordScore?.toFloat() ?: 0.0f
            }
        }
    }

    // total 높이 : 85(70+15) 드래그 탑 아이콘 까지 고려
    Box(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.romantic_w))
            .height(dimensionResource(id = R.dimen.romantic_total_h))
    ){
        // custom box (배경) (____)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.romantic_h))
                .align(Alignment.BottomCenter)
                .background(
                    color = colorResource(id = R.color.romantic_bar_color),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_16))
                )
                .blur(60.dp)
                .coustomShadow(
                    color = colorResource(id = R.color.shadow_color_romantic),
                    offsetY = dimensionResource(id = R.dimen.padding_4),
                    blurRadius = dimensionResource(id = R.dimen.size_30)
                )
        ){
            BarInternalContent() // 배경 내부 뷰

            // 그라데이션 뷰
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_31),
                        bottom = dimensionResource(id = R.dimen.padding_31)
                    )
            ){
                ProgressGradient(progressAnimation,currentOffset.value)
            }

            // 투명 스크롤러 - 드래그 관리 !
            Box(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_40),
                        end = dimensionResource(id = R.dimen.padding_40),
                        top = dimensionResource(id = R.dimen.padding_13),
                        bottom = dimensionResource(id = R.dimen.padding_13)
                    )
                    .width(dimensionResource(id = R.dimen.size_244))
                    .height(dimensionResource(id = R.dimen.size_44))
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

        ProgressGraphic(progress = progressAnimation, offset = currentOffset.value) //현재 상태 아이콘
    }
}