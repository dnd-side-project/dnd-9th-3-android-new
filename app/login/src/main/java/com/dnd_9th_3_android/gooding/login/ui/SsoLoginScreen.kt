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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.login.data.domain.GoogleLoginInterface
import com.dnd_9th_3_android.gooding.login.data.domain.KaKaoLoginInterface
import com.dnd_9th_3_android.gooding.login.viewModel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.kakao.sdk.auth.model.OAuthToken

@Composable
fun SsoLoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    // 뒤로가기 제어
    BackHandler(enabled = true, onBack = {})

    val firebaseAuth = FirebaseAuth.getInstance()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(), onResult ={ result->
            viewModel.setLauncher(result,firebaseAuth)
        }
    )
    val callback : (OAuthToken?,Throwable?) -> Unit = { token, error->
        viewModel.setCallback(error, token)
    }

    var isGoogleLogin by remember{ mutableStateOf(false) }
    var isKaKaoLogin by remember{ mutableStateOf(false) }

    if (isGoogleLogin){
        viewModel.goggleLogin(launcher)
        isGoogleLogin = false
    }
    if (isKaKaoLogin){
        if (!viewModel.checkKaKaoLogin()) {
            viewModel.kaKaoLogin(callback)
        }
        isKaKaoLogin = false
    }
    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .wrapContentSize()
                .padding(
                    top = dimensionResource(id = R.dimen.padding_181),
                )
                .align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(id = R.drawable.gooding_splash_text),
                contentDescription = null,
                modifier = Modifier
                    .wrapContentWidth()
                    .height(dimensionResource(id = R.dimen.size_37))
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_14)))

            Text(
                text = "굳잉과 함께 나만의 굳이데이 기록을 남겨보세요",
                fontSize = dimensionResource(id = R.dimen.text_14_sp).value.sp,
                color = colorResource(id = R.color.blue_gray_2),
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.BottomCenter)
                .padding(
                    bottom = dimensionResource(id = R.dimen.padding_80),
                    start = dimensionResource(id = R.dimen.padding_18),
                    end = dimensionResource(id = R.dimen.padding_18)
                )
        ){
            Image(
                painter = painterResource(id = R.drawable.kakao_login_image),
                contentDescription = null,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.size_324))
                    .height(dimensionResource(id = R.dimen.size_47))
                    .clickable {
                        isKaKaoLogin = true
                    }
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_12)))
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = null,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.size_324))
                    .height(dimensionResource(id = R.dimen.size_47))
                    .clickable {
                        isGoogleLogin = true
                    }
            )
        }
    }
}