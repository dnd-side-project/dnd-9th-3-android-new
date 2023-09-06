package com.dnd_9th_3_android.gooding.login.navi

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dnd_9th_3_android.gooding.login.ui.CheckCategoryScreen
import com.dnd_9th_3_android.gooding.login.ui.FinishScreen
import com.dnd_9th_3_android.gooding.login.ui.NickNameScreen
import com.dnd_9th_3_android.gooding.login.viewModel.LoginViewModel

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
        startDestination = "nickNameScreen"
    ){
        composable("nickNameScreen"){
            onProgressChange(33f)
            NickNameScreen(navController, onStepChange = {
                onStepChange(it)
            },loginViewModel)
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