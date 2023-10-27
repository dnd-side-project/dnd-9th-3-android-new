package com.dnd_9th_3_android.gooding.login

//import androidx.compose.foundation.layout.Box
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dnd_9th_3_android.gooding.login.data.domain.GoogleLoginInterface
import com.dnd_9th_3_android.gooding.login.data.domain.KaKaoLoginInterface
import com.dnd_9th_3_android.gooding.login.navi.MainNaviGraph
import com.kakao.sdk.auth.model.OAuthToken
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.login.viewModel.LoginViewModel
import com.dnd_9th_3_android.gooding.model.user.AccessToken
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//import androidx.navigation.compose.rememberNavController
//import com.dnd_9th_3_android.gooding.login.navi.MainNaviGraph

// start Screen
@Composable
fun LoginScreen(
    naviController: NavHostController
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.blue_gray_7)))
    // main login
    {
        MainNaviGraph(navController = naviController)
        rememberCoroutineScope().launch {
            delay(500)
            naviController.navigate("ssoScreen")
        }
    }

}