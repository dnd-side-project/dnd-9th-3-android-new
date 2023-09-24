package com.dnd_9th_3_android.gooding.my.mainLayout

import android.view.View
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dnd_9th_3_android.gooding.my.BottomNaviLocator
import com.google.android.material.bottomnavigation.BottomNavigationView

@Composable
fun SettingScreen(
    onBackPress : () -> Unit
) {
//    BottomNaviLocator.stateChange(bottomNavi,false)
    // 뒤로가기 동작 제어
    BackHandler(enabled = true, onBack = {
//        BottomNaviLocator.stateChange(bottomNavi,true)
        onBackPress()
    })
}