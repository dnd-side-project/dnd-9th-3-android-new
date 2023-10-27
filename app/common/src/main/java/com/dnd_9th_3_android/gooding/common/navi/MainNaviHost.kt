package com.dnd_9th_3_android.gooding.common.navi

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dnd_9th_3_android.gooding.common.bottomBar.BottomBar
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.my.mainScreen.SettingScreen
import com.dnd_9th_3_android.gooding.record.RecordScreen
import com.dnd_9th_3_android.gooding.record.tabFinish.FinishRecordScreen
import com.dnd_9th_3_android.gooding.record.tabGallery.GalleryScreen
import com.dnd_9th_3_android.gooding.record.tabMain.MainRecordScreen
import com.dnd_9th_3_android.gooding.search.SearchScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNaviHost(
    appState : ApplicationState
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = appState.scaffoldState,
        bottomBar = { BottomBar(appState = appState)}
    ) { innerPadding ->
        NavHost(
            appState.navController,
            startDestination = ScreenRoot.MAIN_GRAPH,
            Modifier.padding(innerPadding)
        ){
            // main view
            bottomGraph(appState)

            //Search view
            composable(ScreenRoot.MAIN_SEARCH){
                SearchScreen(appState)
            }

            // my Setting view
            composable(ScreenRoot.MY_SETTING){
                SettingScreen()
            }

            composable(ScreenRoot.MAIN_RECORD){
                RecordScreen(appState)
            }
        }
    }
}