package com.dnd_9th_3_android.gooding.login

//import androidx.compose.foundation.layout.Box
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.rememberNavController
import com.dnd_9th_3_android.gooding.login.data.GoogleLoginInterface
import com.dnd_9th_3_android.gooding.login.data.KaKaoLoginInterface
import com.dnd_9th_3_android.gooding.login.navi.MainNaviGraph
import com.kakao.sdk.auth.model.OAuthToken
import com.dnd_9th_3_android.gooding.core.data.R
//import androidx.navigation.compose.rememberNavController
//import com.dnd_9th_3_android.gooding.login.navi.MainNaviGraph

// start Screen
@Composable
fun LoginScreen(
    launcher: ActivityResultLauncher<Intent>,
    callback : (OAuthToken?, Throwable?) -> Unit,
    kaKaoLogin : KaKaoLoginInterface,
    googleLogin : GoogleLoginInterface,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.blue_gray_7)))
    // main login
    {
        val navController = rememberNavController()
        MainNaviGraph(navController = navController,launcher,callback,kaKaoLogin,googleLogin)
    }
}