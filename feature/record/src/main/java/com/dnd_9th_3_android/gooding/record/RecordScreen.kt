package com.dnd_9th_3_android.gooding.record

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.record.navi.RecordGraph
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel

@Composable
fun RecordScreen(
    appState : ApplicationState
) {
    // 뒤로가기 제어 -> 이전 screen으로 이동
    BackHandler(
        enabled = true,
        onBack = {
            appState.navController.popBackStack()
        }
    )

    // 그래프 생성
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.blue_gray_7))
    ){
        RecordGraph(appState)
    }
}