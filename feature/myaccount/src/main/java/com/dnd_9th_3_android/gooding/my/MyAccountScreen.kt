package com.dnd_9th_3_android.gooding.my

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.my.mainScreen.MyScreen
import com.dnd_9th_3_android.gooding.my.viewModel.MyOptionViewModel

// just nav controller view
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

    MyScreen()
}