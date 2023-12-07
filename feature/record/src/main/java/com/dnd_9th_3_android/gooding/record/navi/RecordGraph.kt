package com.dnd_9th_3_android.gooding.record.navi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.record.state.rememberRecordState
import com.dnd_9th_3_android.gooding.record.tabFinish.FinishRecordScreen
import com.dnd_9th_3_android.gooding.record.tabGallery.GalleryScreen
import com.dnd_9th_3_android.gooding.record.tabMain.MainRecordScreen
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel

@Composable
fun RecordGraph(
    appState : ApplicationState
) {
    val navi = rememberNavController()
    val recordState = rememberRecordState()

    NavHost(
        navController = navi,
        startDestination = "mainRecordScreen"
    ){

        composable("galleryScreen") { entry->
            val parentEntry  = remember(entry) { navi.getBackStackEntry("mainRecordScreen")}
            GalleryScreen(viewModel = hiltViewModel(parentEntry))
        }
        composable(
            "mainRecordScreen"
        ) {
            MainRecordScreen(appState,navi,recordState)
        }
        composable(
            "finishRecordScreen"
        ) { entry ->
            val parentEntry  = remember(entry) { navi.getBackStackEntry("mainRecordScreen")}
            FinishRecordScreen(viewModel = hiltViewModel(parentEntry) )
        }
    }
}