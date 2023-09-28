package com.dnd_9th_3_android.gooding.data.customRowTab

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// size 조절 참고
//https://stackoverflow.com/questions/76503595/reduce-spacing-between-scrollable-tabs-in-compose/76533630#76533630
//https://stackoverflow.com/questions/76503595/reduce-spacing-between-scrollable-tabs-in-compose/76533630#76533630
@OptIn(ExperimentalPagerApi::class)
@SuppressLint("RememberReturnType")
@Composable
fun CustomRowTabBar(
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    pages : List<String>,
    textShadow: Shadow,
    fontFamily: FontFamily,
    fontSize : TextUnit,
    fontSelectColor : Color,
    fontUnSelectColor : Color,
    boxHeight : Dp,
    horizontalMargin : Dp,
    indicator : Boolean
) {
    val boxHeightModifier = if (boxHeight==0.dp){
        Modifier.wrapContentSize()
    } else{
        Modifier
            .height(boxHeight)
            .wrapContentWidth()

    }
    Row(
        modifier = boxHeightModifier,
        horizontalArrangement = Arrangement.spacedBy(horizontalMargin)
    ) {
        pages.forEachIndexed { index, title ->
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clickable {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                // 선택 텍스트
                Column(
                    modifier = Modifier
                        .wrapContentSize(),
                    horizontalAlignment = CenterHorizontally
                ) {
                    // 선택 텍스트
                    if (index == pagerState.currentPage) {
                        CustomText(
                            title,
                            textShadow,
                            fontFamily,
                            fontSize,
                            fontSelectColor
                        )
                    }
                    // 비 선택 텍스트
                    else {
                        CustomText(
                            title,
                            textShadow,
                            fontFamily,
                            fontSize,
                            fontUnSelectColor
                        )
                    }
                    if (indicator) { Spacer(modifier = Modifier.height(16.dp)) }
                    // indicator
                    if (indicator) {
                        Box(
                            Modifier
                                .height(2.dp)
                                .background(if (index == pagerState.currentPage) Color.White else Color.Transparent)
                                .width(if (index==0) 75.dp else 48.dp)
                        )
                    }
                }
            }
        }
    }

}