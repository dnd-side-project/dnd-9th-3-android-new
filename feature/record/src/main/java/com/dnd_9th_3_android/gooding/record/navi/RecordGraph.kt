package com.dnd_9th_3_android.gooding.record.navi

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.record.RecordScreen
import com.dnd_9th_3_android.gooding.record.state.rememberRecordState
import com.dnd_9th_3_android.gooding.record.tabFinish.FinishRecordScreen
import com.dnd_9th_3_android.gooding.record.tabGallery.GalleryScreen
import com.dnd_9th_3_android.gooding.record.tabLocation.ui.LocationScreen
import com.dnd_9th_3_android.gooding.record.tabMain.MainRecordScreen
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel

@Composable
fun RecordGraph(
    recordNavi: NavHostController,
    appState: ApplicationState,
) {
    val recordState = rememberRecordState()

    NavHost(
        navController =  recordNavi,
        route = ScreenRoot.MAIN_RECORD,
        startDestination = ScreenRoot.SUB_GALLERY,
    ) {

        composable(ScreenRoot.SUB_GALLERY) { entry ->
            val parentEntry = remember(entry) {  appState.navController.getBackStackEntry(ScreenRoot.MAIN_RECORD) }
            GalleryScreen(viewModel = hiltViewModel(parentEntry))
        }

        composable(
            ScreenRoot.SUB_RECORD
        ) { entry ->
            val parentEntry = remember(entry) {  appState.navController.getBackStackEntry(ScreenRoot.MAIN_RECORD) }
            MainRecordScreen(recordState,viewModel = hiltViewModel(parentEntry))
        }
        composable(
            ScreenRoot.SUB_FINISH
        ) { entry ->
            val parentEntry = remember(entry) {  appState.navController.getBackStackEntry(ScreenRoot.MAIN_RECORD)  }
            FinishRecordScreen(recordState,
//                viewModel = hiltViewModel(parentEntry)
            )
        }

        composable(
            ScreenRoot.LOCATION
        ) { entry ->
            val parentEntry = remember(entry) {  appState.navController.getBackStackEntry(ScreenRoot.MAIN_RECORD)  }
            LocationScreen(recordState,viewModel = hiltViewModel(parentEntry))
        }
    }
}





