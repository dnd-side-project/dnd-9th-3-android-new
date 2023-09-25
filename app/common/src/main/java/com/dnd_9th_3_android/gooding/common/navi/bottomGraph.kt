package com.dnd_9th_3_android.gooding.common.navi

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dnd_9th_3_android.gooding.common.bottomBar.BottomScreen
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.feed.FeedScreen
import com.dnd_9th_3_android.gooding.my.MyAccountScreen
import com.dnd_9th_3_android.gooding.record.RecordScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.bottomGraph(appState : ApplicationState) {
    navigation(
        startDestination = BottomScreen.Feed.route, route= ScreenRoot.MAIN_GRAPH
    ){
        composable(BottomScreen.Feed.route){
            FeedScreen(appState)
        }
        composable(BottomScreen.Record.route){
            RecordScreen(appState)
        }
        composable(BottomScreen.MyAccount.route) {
            MyAccountScreen(appState)
        }
    }
}