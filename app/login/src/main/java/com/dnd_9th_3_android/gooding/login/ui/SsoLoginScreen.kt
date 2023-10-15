package com.dnd_9th_3_android.gooding.login.ui

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.dnd_9th_3_android.gooding.core.data.R
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dnd_9th_3_android.gooding.data.SplashLayer
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.login.data.domain.GoogleLoginInterface
import com.dnd_9th_3_android.gooding.login.data.domain.KaKaoLoginInterface
import com.dnd_9th_3_android.gooding.login.type.WaitState
import com.dnd_9th_3_android.gooding.login.viewModel.LoginViewModel
import com.dnd_9th_3_android.gooding.model.user.UserData
import com.google.firebase.auth.FirebaseAuth
import com.kakao.sdk.auth.model.OAuthToken

@Composable
fun SsoLoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    // 뒤로가기 제어
    BackHandler(enabled = true, onBack = {})

    val userState : MutableState<UserData?> = remember{ mutableStateOf(null)}
    val firebaseAuth = FirebaseAuth.getInstance()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(), onResult = { result ->
            viewModel.setLauncher(result, firebaseAuth, loginState = { state -> userState.value = state })
        }
    )
    val callback : (OAuthToken?,Throwable?) -> Unit = { token, error->
        viewModel.setCallback(error, token, loginState =  {state-> userState.value = state })
    }

    var isGoogleLogin by remember{ mutableStateOf(false) }
    var isKaKaoLogin by remember{ mutableStateOf(false) }
    if (userState.value!=null) {
        WaitState(navController,userState.value, LocalContext.current.applicationContext, finish = {
            isGoogleLogin = false
            isKaKaoLogin = false
        })
    }


    if (isGoogleLogin){ viewModel.goggleLogin(launcher) }
    if (isKaKaoLogin){
        if (!viewModel.checkKaKaoLogin()) {
            viewModel.kaKaoLogin(callback)
        }
    }


    if (isGoogleLogin || isKaKaoLogin || userState.value != null){
        SplashLayer() // 로딩 상태
    }else {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 181.dp, start = 1.dp)
                    .align(Alignment.TopCenter)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.gooding_splash_text),
                    contentDescription = null,
                    modifier = Modifier.wrapContentWidth().height(37.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "굳잉과 함께 나만의 굳이데이 기록을 남겨보세요",
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.blue_gray_2),
                    fontFamily = pretendardBold
                )
            }

            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.BottomCenter)
                    .padding(
                        bottom = 80.dp,
                        start = 18.dp, end = 18.dp
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.login_kakao),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(47.dp)
                        .clickable { isKaKaoLogin = true }
                )
                Spacer(modifier = Modifier.height(12.dp))
                Image(
                    painter = painterResource(id = R.drawable.login_google),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(47.dp)
                        .clickable { isGoogleLogin = true }
                )
            }
        }
    }
}