package com.dnd_9th_3_android.gooding.login.type

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.dnd_9th_3_android.gooding.model.user.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WaitState(
    controller: NavHostController,
    userData : UserData?,
    context: Context,
    finish : () -> Unit
) {
    when (userData?.onboardYn) {
        "Y" -> { // onBoarding 완료
            // main으로 이동
            val intent = Intent(
                context,
                Class.forName("com.dnd_9th_3_android.gooding.MainActivity")
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            finish()
        }
        else -> {
            controller.navigate("onBoardingScreen")
            finish()
        }
    }
}