package com.dnd_9th_3_android.gooding.login

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.dnd_9th_3_android.gooding.core.data.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.dnd_9th_3_android.gooding.data.customProgress.CustomProgressBar
import com.dnd_9th_3_android.gooding.login.viewModel.LoginViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dnd_9th_3_android.gooding.login.type.BottomTextBoxType

@Composable
fun OnBoardingMainScreen(
    nextStepButtonType : Int,
    navController : NavHostController,
    isClick : (Int) -> Unit,
    progress : Float,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    var isNextClick by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        // top box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.size_97))
                .align(Alignment.TopCenter)
        ) {
            // back button
            Box(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_11),
                        top = dimensionResource(id = R.dimen.padding_55)
                    )
                    .size(dimensionResource(id = R.dimen.padding_24))
                    .clickable {
                        // pop back stack
                        if (progress!=33f){
                            navController.popBackStack()
                        }
                    }
                    .align(Alignment.TopStart)
                , contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_left_svg),
                    contentDescription = null,
                )
            }

            CustomProgressBar(
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.padding_4)))
                    .align(Alignment.BottomStart)
                    .height(dimensionResource(id = R.dimen.padding_4)),
                width = LocalConfiguration.current.screenWidthDp.dp,
                backgroundColor = colorResource(id = R.color.blue_gray_5),
                foregroundColor = Brush.horizontalGradient(
                    listOf(colorResource(id = R.color.secondary_1),colorResource(id = R.color.secondary_1))
                ),
                percent = progress.toInt(),
                isShownText = false
            )
        }

        // 바텀 버튼  버튼
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomCenter)
                .padding(
                    start = dimensionResource(id = R.dimen.padding_18),
                    end = dimensionResource(id = R.dimen.padding_18),
                    bottom = dimensionResource(id = R.dimen.padding_28)
                )
                .clickable {
                    isNextClick = true
                }
        ) {
            BottomTextBoxType(nextStepButtonType)
        }

        // 다음 동작 제어
        if (isNextClick) {
            if (progress == 33f && nextStepButtonType == 1) {
                isClick(2)
                navController.navigate("checkCategoryScreen")
            } else if (progress == 66f && nextStepButtonType == 1) {
                isClick(2)
                navController.navigate("finishScreen")
            } else if (progress == 100f && nextStepButtonType == 3) {
                isClick(4)
                // main으로 이동
                val intent = Intent(
                    LocalContext.current.applicationContext,
                    Class.forName("com.dnd_9th_3_android.gooding.MainActivity")
                )
                LocalContext.current.startActivity(intent)
            }
            isNextClick = false
        }
    }
}