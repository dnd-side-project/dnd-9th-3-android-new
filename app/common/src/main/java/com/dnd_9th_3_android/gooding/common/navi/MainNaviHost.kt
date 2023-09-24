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
import com.dnd_9th_3_android.gooding.common.root.ScreenRoot
import com.dnd_9th_3_android.gooding.common.root.ScreenRoot.MAIN_GRAPH
import com.dnd_9th_3_android.gooding.common.state.ApplicationState
import com.dnd_9th_3_android.gooding.feed.FeedScreen

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
            startDestination = ScreenRoot.MAIN_FEED,
            Modifier.padding(innerPadding)
        ){
            // main view
            composable(ScreenRoot.MAIN_FEED){
                FeedScreen()
            }
            composable(ScreenRoot.MAIN_GRAPH){
                mainGraph(appState)
            }
        }
    }
}