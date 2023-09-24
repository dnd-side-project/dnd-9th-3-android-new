package com.dnd_9th_3_android.gooding.common.navi

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dnd_9th_3_android.gooding.common.bottomBar.BottomScreen
import com.dnd_9th_3_android.gooding.common.root.ScreenRoot
import com.dnd_9th_3_android.gooding.common.state.ApplicationState
import com.dnd_9th_3_android.gooding.feed.FeedScreen
import com.dnd_9th_3_android.gooding.my.MyAccountScreen
import com.dnd_9th_3_android.gooding.search.SearchScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainGraph(appState : ApplicationState) {
    navigation(
        startDestination = BottomScreen.Feed.route, route= ScreenRoot.MAIN_GRAPH
    ){
        composable(BottomScreen.Feed.route){
            FeedScreen()
        }
        composable(BottomScreen.Record.route){
            // Record
            SearchScreen()
        }
        composable(BottomScreen.Feed.route){
            MyAccountScreen()
        }
    }
}