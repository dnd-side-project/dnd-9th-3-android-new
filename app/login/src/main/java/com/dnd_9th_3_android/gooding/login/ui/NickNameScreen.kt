package com.dnd_9th_3_android.gooding.login.ui

import androidx.activity.compose.BackHandler
import androidx.navigation.NavHostController
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.login.component.EditTextBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.login.viewModel.LoginViewModel
import com.dnd_9th_3_android.gooding.login.viewModel.OnBoardingObject

@Composable
fun NickNameScreen(
    navController: NavHostController,
    onStepChange : (Int) -> Unit
) {
    // info update - > 나중에 수정
    onStepChange(0)

    // 뒤로가기 제어
    BackHandler(enabled = true, onBack = {})

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 133.dp)
    ) {
        Text(
            text = "굳잉에서 사용할",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(
                start = 18.dp,
            )
        )

        Text(
            text = "닉네임을 설정해주세요.",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(
                start = 18.dp,
            )
        )

        Spacer(modifier = Modifier.height(40.dp))

        var text by remember {
            mutableStateOf(TextFieldValue(OnBoardingObject.userName))
        }
        var possibleText by remember {
            mutableStateOf(true)
        }

        var errorType by remember { //error 1 = 닉네임 , 2 = 글자수
            mutableStateOf(0)
        }

        EditTextBox(
            text,
            possibleText,
            "닉네임 입력 (15자 이내)",
            onTexting = {
                OnBoardingObject.userName = it.text
                text = it
                if (text.text.length > 15) { // 글자 수 초과
                    possibleText = false
                    errorType = 2
                } else if (text.text=="굳잉" || text.text=="gooding") {
                    // 닉네임 중복
                    possibleText = false
                    errorType = 1
                } else {
                    possibleText = true
                }
            }
        )

        if (errorType == 1 && !possibleText) {
            Box(
                modifier = Modifier.padding(
                    start = 18.dp,
                    top = 8.dp
                )
            ) {
                // 155 21
                Image(
                    painter = painterResource(id = R.drawable.nickname_error_1),
                    modifier = Modifier
                        .width(155.dp)
                        .height(21.dp),
                    contentDescription = null
                )
            }
        } else if (errorType == 2 && !possibleText) {
            Box(
                modifier = Modifier.padding(
                    start = 18.dp,
                    top = 8.dp
                )
            ) {
                // 155 21
                Image(
                    painter = painterResource(id = R.drawable.nickname_error_2),
                    modifier = Modifier
                        .width(155.dp)
                        .height(21.dp),
                    contentDescription = null
                )
            }
        }

        if (possibleText && text.text.length>0){
            onStepChange(1)
        }else if (!possibleText && text.text.length>0){
            onStepChange(0)
        }


    }

}