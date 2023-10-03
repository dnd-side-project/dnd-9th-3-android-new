package com.dnd_9th_3_android.gooding.my.mainScreen

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
fun SettingScreen(
    onBackPress : () -> Unit
) {
    // 뒤로가기 동작 제어
    BackHandler(enabled = true, onBack = {
        onBackPress()
    })
}