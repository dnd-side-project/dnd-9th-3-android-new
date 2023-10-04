package com.dnd_9th_3_android.gooding.my

import androidx.activity.compose.BackHandler
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.data.state.rememberMyState
import com.dnd_9th_3_android.gooding.my.mainScreen.MyScreen
import com.dnd_9th_3_android.gooding.my.viewModel.MyOptionViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

// just nav controller view
@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun MyAccountScreen(
    appState : ApplicationState,
    viewModel : MyOptionViewModel = hiltViewModel()
) {
    // 뒤로가기 동작 제어
    BackHandler(enabled = true, onBack = {})

    // app State 저장
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    viewModel.initAppState(appState, screenWidth)

    // my State
    val myAccountState = rememberMyState()
    viewModel.initMyAccountState(myAccountState)

    MyScreen()

    // 메인으로 옮기기
//    if (showDeleteView){
//        DeleteFeedBottomSheet(0,
//            onDelete = {
//                // delete feed/ //
//            },
//            onClose = {
//                // close view
//                showDeleteView = false
//            },
//        )
//    }
//    if (showSelectView){
//        SelectMonthBottomSheet(onClose = {
//            todayViewModel.monthPicker.apply {
//                if (!this.isChange){
//                    this.resetData()
//                }else{
//                    // 데이터 초기화
//                    this.isChange = false
//                    timeLineViewModel.loadData = currentKey
//                }
//            }
//            showSelectView = false
//
//        })
//    }

//    + bottom 확장 상태에 따라서 상태 변환 !
}