package com.dnd_9th_3_android.gooding.data.state

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.material.rememberSwipeableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun rememberMyState(
    swipingState : SwipeableState<SwipingStates> = rememberSwipeableState(initialValue = SwipingStates.EXPANDED),
    bottomExtendState : MutableState<Boolean> = remember { mutableStateOf(false) },
    bottomPageState : PagerState = rememberPagerState(),
    // -1 : off , over 0 : index ( or id )
    showDeleteView : MutableState<Int> = remember{ mutableStateOf(-1) },
    showMonthPickerView : MutableState<Boolean> = remember { mutableStateOf(false)},
) = remember(swipingState,bottomExtendState,bottomPageState,showDeleteView,showMonthPickerView){
    MyAccountState(
        swipingState,
        bottomExtendState,
        bottomPageState,
        showDeleteView,
        showMonthPickerView,
    )
}