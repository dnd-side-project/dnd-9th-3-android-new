package com.dnd_9th_3_android.gooding.record

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import com.dnd_9th_3_android.gooding.data.state.ApplicationState

@Composable
fun RecordScreen(appState : ApplicationState) {
    // 뒤로가기 제어 -> 이전 screen으로 이동
    BackHandler(
        enabled = true,
        onBack = {
            appState.navController.popBackStack()
        }
    )
}