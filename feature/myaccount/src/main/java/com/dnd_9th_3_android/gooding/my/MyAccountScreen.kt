package com.dnd_9th_3_android.gooding.my

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.data.state.SwipingStates
import com.dnd_9th_3_android.gooding.data.state.rememberMyState
import com.dnd_9th_3_android.gooding.my.itemFeed.DeleteFeedBottomSheet
import com.dnd_9th_3_android.gooding.my.mainScreen.MyScreen
import com.dnd_9th_3_android.gooding.my.selectMonth.SelectMonthBottomSheet
import com.dnd_9th_3_android.gooding.my.viewModel.MyOptionViewModel
import com.dnd_9th_3_android.gooding.my.viewModel.TimeLineViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

// just nav controller view
@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun MyAccountScreen(
    appState : ApplicationState,
    viewModel : MyOptionViewModel = hiltViewModel(),
    timeLineViewModel: TimeLineViewModel = hiltViewModel()
) {


    // app State 저장
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    viewModel.initAppState(appState, screenWidth)

    // my State
    val myAccountState = rememberMyState()
    viewModel.initMyAccountState(myAccountState)

    // main view
    MyScreen()


    // bottom sheet state
    viewModel.myAccountState?.let{ mySate ->

        if (mySate.showDeleteView.value!=-1){
            DeleteFeedBottomSheet(
                feedId = mySate.showDeleteView.value,
                onClose = {
                    // close
                    mySate.showDeleteView.value = -1
                },
                onDelete ={
                    // feed delete

                }
            )
        }

        if (mySate.showMonthPickerView.value){
            SelectMonthBottomSheet(
                onClose = {
                    viewModel.closeMonthPicker()
                }
            )
        }

        // extended state
        mySate.bottomExtendState.value =
            mySate.swipingState.currentValue == SwipingStates.COLLAPSED


        // extended state -> bottom
        val current = appState.navController.currentDestination?.route
        if (current == ScreenRoot.MAIN_MY){
            appState.bottomBarState.value = !mySate.bottomExtendState.value
        }
    }
}