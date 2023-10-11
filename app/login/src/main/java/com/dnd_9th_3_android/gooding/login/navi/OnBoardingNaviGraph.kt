package com.dnd_9th_3_android.gooding.login.navi

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dnd_9th_3_android.gooding.api.UserInfoSharedPreferences
import com.dnd_9th_3_android.gooding.data.SplashLayer
import com.dnd_9th_3_android.gooding.login.ui.CheckCategoryScreen
import com.dnd_9th_3_android.gooding.login.ui.FinishScreen
import com.dnd_9th_3_android.gooding.login.ui.NickNameScreen
import com.dnd_9th_3_android.gooding.login.viewModel.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OnBoardingNaviGraph(
    navController: NavHostController,
    onStepChange : (Int) -> Unit,
    onProgressChange : (Float) -> Unit,
    loginViewModel : LoginViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = "splashScreen"
    ){
        composable("splashScreen"){
            SplashLayer()
            rememberCoroutineScope().launch {
                delay(1000)
                navController.navigate("nickNameScreen")
            }
        }
        composable("nickNameScreen"){
            onProgressChange(33f)
            NickNameScreen(navController, onStepChange = {
                onStepChange(it)
            })
        }
        composable("checkCategoryScreen"){
            onProgressChange(66f)
            CheckCategoryScreen(navController, onStepChange = {
                onStepChange(it)
            },loginViewModel)
        }
        composable("finishScreen"){
            onProgressChange(100f)
            FinishScreen(navController, onStepChange = {
                onStepChange(it)
            })
        }
    }
}