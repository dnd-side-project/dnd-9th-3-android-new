package com.dnd_9th_3_android.gooding.login.navi

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
    nextStepButtonType : MutableState<Int>,
    onProgressChange : (Float) -> Unit,
    loginViewModel : LoginViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "nickNameScreen"
    ){
        composable("nickNameScreen"){
            onProgressChange(33f)
            NickNameScreen( onStepChange = {
                nextStepButtonType.value = it
            },loginViewModel)
        }
        composable("checkCategoryScreen"){
            onProgressChange(66f)
            CheckCategoryScreen( onStepChange = {
                nextStepButtonType.value = it
            },loginViewModel)
        }
        composable("finishScreen"){
            onProgressChange(100f)
            FinishScreen( onStepChange = {
                nextStepButtonType.value = it
            },loginViewModel)
        }
    }
}