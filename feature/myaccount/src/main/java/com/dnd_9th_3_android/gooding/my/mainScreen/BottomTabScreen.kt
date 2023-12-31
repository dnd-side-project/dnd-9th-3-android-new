package com.dnd_9th_3_android.gooding.my.subLayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.my.mainTabLayout.SaveFeedScreen
import com.dnd_9th_3_android.gooding.my.mainTabLayout.TimeLineScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.customRowTab.CustomRowTabBar
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.data.customRowTab.myPages
import com.dnd_9th_3_android.gooding.data.state.SwipingStates
import com.dnd_9th_3_android.gooding.my.viewModel.MyOptionViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


// bottom 확장 뷰
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun BottomTabScreen(
    setMaxScreen : ()->Unit, //원래 뷰로 돌아가기
    viewModel: MyOptionViewModel = hiltViewModel()
){
    val pageState = viewModel.myAccountState?.bottomPageState.let{pagerState ->
         pagerState ?: rememberPagerState()
    }
    val coroutineScope = viewModel.applicationState?.coroutineScope.let{coroutineScope ->
        coroutineScope ?: rememberCoroutineScope()
    }
    val bottomExtend = viewModel.myAccountState?.bottomExtendState.let{ extendedState->
        extendedState ?: mutableStateOf(false)
    }

    Column(
        modifier =
        if (!bottomExtend.value) Modifier
            .background(
                color = colorResource(id = R.color.tab_background),
                RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp
                )
            )
        else Modifier.background(colorResource(id = R.color.tab_background))
        ,horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        // button : 축소
        if (bottomExtend.value){
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        bottomExtend.value = false
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
            Spacer(modifier = Modifier.height(3.dp))
        }
        Spacer(modifier = Modifier.height(6.dp))

        // tab selector
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(18.dp))
            CustomRowTabBar(
                pagerState = pageState,
                coroutineScope = coroutineScope,
                pages = myPages,
                textShadow = Shadow(
                    color = colorResource(id = R.color.tab_shadow_25),
                    blurRadius = 4f
                ),
                fontFamily = pretendardBold,
                fontSize = 16.sp,
                fontSelectColor = Color.White,
                fontUnSelectColor= colorResource(id = R.color.blue_gray_3),
                boxHeight = 0.dp,
                horizontalMargin = 7.dp,
                indicator = true
            )
        }
        // line indicator
        Divider(
            modifier = Modifier
                .background(colorResource(id = R.color.blue_gray))
                .height(1.dp)
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

