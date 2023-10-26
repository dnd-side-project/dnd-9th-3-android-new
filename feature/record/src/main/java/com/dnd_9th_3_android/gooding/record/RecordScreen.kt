package com.dnd_9th_3_android.gooding.record

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.record.viewModel.RecordOptionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun RecordScreen(
    appState : ApplicationState,
    viewModel: RecordOptionViewModel = hiltViewModel()
) {
    // 뒤로가기 제어 -> 이전 screen으로 이동
    BackHandler(
        enabled = true,
        onBack = {
            appState.navController.popBackStack()
        }
    )

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val imageHeight = 180.dp * (screenWidth/(360.dp))
    viewModel.initAppState(appState,screenWidth,imageHeight)

    // 갤러리 선택으로 이동
    viewModel.goGalleryRecord()
}