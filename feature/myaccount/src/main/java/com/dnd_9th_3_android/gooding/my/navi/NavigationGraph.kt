package com.dnd_9th_3_android.gooding.my.navi

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.my.mainScreen.MyScreen
import com.dnd_9th_3_android.gooding.my.mainScreen.SettingScreen
import com.dnd_9th_3_android.gooding.my.viewModel.MyOptionViewModel


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(
    appState : ApplicationState,
    viewModel : MyOptionViewModel = hiltViewModel()
){
    // app State 저장
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    viewModel.initAppState(
        appState,
        screenWidth
    )

    NavHost(
        navController = appState.navController,
        startDestination = ScreenRoot.MY_SCREEN
    ){

        composable(ScreenRoot.MY_SCREEN){ MyScreen() }
        composable(ScreenRoot.MY_SETTING){
            SettingScreen(
                onBackPress = { viewModel.applicationState?.navController?.popBackStack() }
            )
        }
    }
}