package com.dnd_9th_3_android.gooding.record.navi

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.record.tabFinish.FinishRecordScreen
import com.dnd_9th_3_android.gooding.record.tabGallery.GalleryScreen
import com.dnd_9th_3_android.gooding.record.tabMain.MainRecordScreen
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel

@Composable
fun RecordGraph(
    appState : ApplicationState,
    viewModel: RecordViewModel = hiltViewModel()
) {
    val navi = rememberNavController()
    val width = LocalConfiguration.current.screenWidthDp.dp
    val height = 180.dp * (width/(360.dp))
    viewModel.recordStateRepository.setState(appState,navi)
    viewModel.recordStateRepository.setSize(height,width)

    NavHost(
        navController = navi,
        startDestination = "galleryScreen"
    ){

        composable("galleryScreen") {
            GalleryScreen(viewModel,navi)
        }
        composable("mainRecordScreen") {
            MainRecordScreen(viewModel)
        }
        composable("finishRecordScreen") {
            FinishRecordScreen(viewModel)
        }
    }
}