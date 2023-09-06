package com.dnd_9th_3_android.gooding.login.navi

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dnd_9th_3_android.gooding.login.SplashScreen
import com.dnd_9th_3_android.gooding.login.data.GoogleLoginInterface
import com.dnd_9th_3_android.gooding.login.data.KaKaoLoginInterface
import com.dnd_9th_3_android.gooding.login.ui.SosLoginScreen
import com.kakao.sdk.auth.model.OAuthToken


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNaviGraph(
    navController: NavHostController,
    launcher: ActivityResultLauncher<Intent>,
    callback : (OAuthToken?, Throwable?) -> Unit,
    kaKaoLogin : KaKaoLoginInterface,
    googleLogin : GoogleLoginInterface,
){
    NavHost(
        navController = navController,
        startDestination = "splashScreen"
    ){
        composable("splashScreen"){ SplashScreen(navController) }
        composable("sosScreen"){ SosLoginScreen(launcher,callback,kaKaoLogin,googleLogin) }
    }
}