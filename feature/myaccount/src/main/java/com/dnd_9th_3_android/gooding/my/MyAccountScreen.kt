package com.dnd_9th_3_android.gooding.my

import android.util.Log
import android.view.View
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.my.navi.NavigationGraph
import com.dnd_9th_3_android.gooding.my.selectMonth.data.MonthPickerInterface
import com.google.android.material.bottomnavigation.BottomNavigationView

// just nav controller view
@Composable
fun MyAccountScreen(appState : ApplicationState) {
    // 뒤로가기 동작 제어
    BackHandler(enabled = true, onBack = {})

    val navController = rememberNavController()
    NavigationGraph(navController)
}