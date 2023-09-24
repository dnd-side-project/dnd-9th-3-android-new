package com.dnd_9th_3_android.gooding.my.navi

import android.util.Log
import android.view.View
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dnd_9th_3_android.gooding.my.BottomNaviLocator
import com.dnd_9th_3_android.gooding.my.mainLayout.MyScreen
import com.dnd_9th_3_android.gooding.my.mainLayout.SettingScreen
import com.google.android.material.bottomnavigation.BottomNavigationView


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = "myScreen"
    ){
        composable("myScreen"){ MyScreen(navController) }
        composable("settingScreen"){SettingScreen(onBackPress = {
//            BottomNaviLocator.stateChange(bottomNavi,true)
            navController.popBackStack()
        })}
    }
}