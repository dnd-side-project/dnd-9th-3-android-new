package com.dnd_9th_3_android.gooding.my.subLayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.my.mainTabLayout.SaveFeedScreen
import com.dnd_9th_3_android.gooding.my.mainTabLayout.TimeLineScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.customRowTab.CustomRowTabBar
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.data.customRowTab.myPages


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomTabScreen(
    isVisibleTop: Boolean,
    setMaxScreen : ()->Unit //원래 뷰로 돌아가기
){
    val pageState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier =
        if (!isVisibleTop) Modifier
            .background(
                color = colorResource(id = R.color.tab_background),
                RoundedCornerShape(
                    topStart = dimensionResource(id = R.dimen.padding_24),
                    topEnd = dimensionResource(id = R.dimen.padding_24)
                )
            )
        else Modifier.background(colorResource(id = R.color.tab_background))
        ,horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_24)))
        // button : 축소
        if (isVisibleTop){
            Box(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.arrow_size))
                    .clickable {
                        setMaxScreen()
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    contentDescription = null,
                    painter = painterResource(id = R.drawable.arrow_bottom_my),
                )
            }
        }else{
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_3)))
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_6)))

        // tab selector
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_18)))
            CustomRowTabBar(
                pagerState = pageState,
                coroutineScope = coroutineScope,
                pages = myPages,
                textShadow = Shadow(
                    color = colorResource(id = R.color.tab_shadow_25),
                    blurRadius = 4f
                ),
                fontFamily = pretendardBold,
                fontSize = dimensionResource(id = R.dimen.text_16_sp).value.sp,
                fontSelectColor = Color.White,
                colorResource(id = R.color.blue_gray_3),
                boxHeight = 0.dp,
                7.dp,
                indicator = true
            )
        }
        // line indicator
        Divider(
            modifier = Modifier
                .background(colorResource(id = R.color.blue_gray))
                .height(dimensionResource(id = R.dimen.border_size))
        )
        // tab pager
        HorizontalPager(
            count = myPages.size,
            state = pageState,
        ) {page ->
            when (page){
                0 -> TimeLineScreen()
                else -> SaveFeedScreen()
            }
        }
    }
}

