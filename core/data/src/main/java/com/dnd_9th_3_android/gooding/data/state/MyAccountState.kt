package com.dnd_9th_3_android.gooding.data.state

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Stable
class MyAccountState(
   val swipingState : SwipeableState<SwipingStates>,
   val bottomExtendState : MutableState<Boolean> ,
   val bottomPageState : PagerState ,
   // -1 : off , over 0 : index ( or id )
   val showDeleteView : MutableState<Int>,
   val showMonthPickerView : MutableState<Boolean>,
   val otherViewState : MutableState<Boolean>
) {}