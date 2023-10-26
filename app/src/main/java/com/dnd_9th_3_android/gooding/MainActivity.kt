package com.dnd_9th_3_android.gooding

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dnd_9th_3_android.gooding.api.NetworkManager
import com.dnd_9th_3_android.gooding.api.UserInfoSharedPreferences
import com.dnd_9th_3_android.gooding.common.navi.MainNaviHost
import com.dnd_9th_3_android.gooding.common.state.ManageBottomBarState
import com.dnd_9th_3_android.gooding.data.state.rememberApplicationState
import com.dnd_9th_3_android.gooding.model.user.AccessToken
import com.dnd_9th_3_android.gooding.model.user.UserData
import com.dnd_9th_3_android.gooding.ui.theme.GoodingTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoodingTheme {
                // 뒤로가기 제어
                BackHandler(enabled = true, onBack = {})

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