package com.dnd_9th_3_android.gooding.login.navi

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dnd_9th_3_android.gooding.data.SplashLayer
import com.dnd_9th_3_android.gooding.login.OnBoardingScreen
import com.dnd_9th_3_android.gooding.login.ui.SsoLoginScreen
import com.kakao.sdk.auth.model.OAuthToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNaviGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = "splashScreen"
    ){
        composable("splashScreen"){
            SplashLayer()
            rememberCoroutineScope().launch {
                delay(1000)
                navController.navigate("ssoScreen")
            }
        }
        composable("ssoScreen"){ SsoLoginScreen() }
        composable("onBoardingScreen"){ OnBoardingScreen(navController) }
    }
}