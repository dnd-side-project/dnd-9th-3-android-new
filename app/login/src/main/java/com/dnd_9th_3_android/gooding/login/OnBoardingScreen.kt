package com.dnd_9th_3_android.gooding.login

import android.content.Intent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.login.navi.OnBoardingNaviGraph
import com.dnd_9th_3_android.gooding.login.viewModel.LoginViewModel

@Composable
fun OnBoardingScreen(
    navController : NavHostController,
    loginViewModel : LoginViewModel = hiltViewModel()
) {
    when (loginViewModel.checkUser()){
        true -> { // onBoarding 완료
            // main으로 이동
            val intent = Intent(
                LocalContext.current.applicationContext,
                Class.forName("com.dnd_9th_3_android.gooding.MainActivity")
            )
            LocalContext.current.startActivity(intent)
        }
        false -> {

        } // onBoaring 진행
        else -> { // 오류
            navController.navigate("ssoScreen")
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.blue_gray_7)))
    // main login
    {
        // 현재 진행 상태
        val currentValue = remember {
            mutableStateOf(0f)
        }
        // progress animation
        val progressAnimDuration = 700
        val progressAnimation by animateFloatAsState(
            targetValue = currentValue.value,
            animationSpec = tween(
                durationMillis = progressAnimDuration, easing = FastOutSlowInEasing
            )
        )

        var nextStepButtonType by remember{
            mutableStateOf(0)
        }
        val onBoardNavController = rememberNavController()
        // 각 화면 네비
        OnBoardingNaviGraph(
            navController = onBoardNavController,
            onStepChange = {
                nextStepButtonType = it
            },
            onProgressChange = {
                currentValue.value = it
            }
        )

        OnBoardingMainScreen(
            nextStepButtonType = nextStepButtonType,
            navController = onBoardNavController,
            progress = progressAnimation,
            isClick = {
                nextStepButtonType = it
            }
        )

    }
}