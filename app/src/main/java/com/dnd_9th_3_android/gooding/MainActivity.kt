package com.dnd_9th_3_android.gooding

import android.os.Bundle
import android.view.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dnd_9th_3_android.gooding.common.navi.MainNaviHost
import com.dnd_9th_3_android.gooding.common.state.ManageBottomBarState
import com.dnd_9th_3_android.gooding.data.state.rememberApplicationState
import com.dnd_9th_3_android.gooding.ui.theme.GoodingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoodingTheme {
                val appState = rememberApplicationState()
                val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
                ManageBottomBarState(
                    navBackStackEntry = navBackStackEntry,
                    bottomBarState = appState.bottomBarState
                )
                MainNaviHost(appState = appState)
            }
        }
    }
}