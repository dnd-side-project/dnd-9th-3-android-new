package com.dnd_9th_3_android.gooding.my

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.my.navi.NavigationGraph

// just nav controller view
@Composable
fun MyAccountScreen(appState : ApplicationState) {
    // 뒤로가기 동작 제어
    BackHandler(enabled = true, onBack = {})
    NavigationGraph(appState)
}