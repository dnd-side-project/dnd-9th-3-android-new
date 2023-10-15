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
fun OnBoardingScreen() {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.blue_gray_7)))
    // main login
    {
        // 현재 진행 상태
        val currentValue = remember { mutableStateOf(0f) }
        // progress animation
        val progressAnimDuration = 700
        val progressAnimation by animateFloatAsState(
            targetValue = currentValue.value,
            animationSpec = tween(
                durationMillis = progressAnimDuration, easing = FastOutSlowInEasing
            )
        )

        val onBoardNavController = rememberNavController()
        val nextStepButtonType = remember{ mutableStateOf(0) }
        val loginViewModel : LoginViewModel = hiltViewModel()
        // 각 화면 네비
        OnBoardingNaviGraph(
            navController = onBoardNavController,
            nextStepButtonType,
            onProgressChange = {
                currentValue.value = it
            },
            loginViewModel
        )

        OnBoardingMainScreen(
            nextStepButtonType = nextStepButtonType,
            navController = onBoardNavController,
            progress = progressAnimation,
            loginViewModel
        )

    }
}