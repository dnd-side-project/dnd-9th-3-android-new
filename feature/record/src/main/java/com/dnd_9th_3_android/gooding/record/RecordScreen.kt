package com.dnd_9th_3_android.gooding.record

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.record.navi.RecordGraph
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel

@Composable
fun RecordScreen(
    appState: ApplicationState,
    viewModel: RecordViewModel = hiltViewModel()
) {
    val viewUpdate = rememberSaveable { mutableStateOf(true) }
    val recordNavi = rememberNavController()
    val width = LocalConfiguration.current.screenWidthDp.dp
    val height = 180.dp * (width / (360.dp))
    if (viewUpdate.value) {
        LaunchedEffect(Unit) {
            viewModel.initState(appState,recordNavi, height, width)
            viewUpdate.value = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.blue_gray_7))
    ) {
        RecordGraph(recordNavi,appState)
    }
}