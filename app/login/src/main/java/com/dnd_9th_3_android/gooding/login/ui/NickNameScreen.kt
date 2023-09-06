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
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.login.viewModel.LoginViewModel
import com.dnd_9th_3_android.gooding.login.viewModel.OnBoardingObject

@Composable
fun NickNameScreen(
    navController: NavHostController,
    onStepChange : (Int) -> Unit,
    loginViewModel : LoginViewModel = hiltViewModel()
) {
    // info update - > 나중에 수정
    loginViewModel.userInfoData()
    onStepChange(0)

    // 뒤로가기 제어
    BackHandler(enabled = true, onBack = {})

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = dimensionResource(id = R.dimen.padding_133))
    ) {
        Text(
            text = "굳잉에서 사용할",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = dimensionResource(id = R.dimen.text_18_sp).value.sp,
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.padding_18),
            )
        )

        Text(
            text = "닉네임을 설정해주세요.",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = dimensionResource(id = R.dimen.text_18_sp).value.sp,
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.padding_18),
            )
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_40)))

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
                    start = dimensionResource(id = R.dimen.padding_18),
                    top = dimensionResource(id = R.dimen.padding_8)
                )
            ) {
                // 155 21
                Image(
                    painter = painterResource(id = R.drawable.nickname_error_1),
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.size_155))
                        .height(dimensionResource(id = R.dimen.padding_21)),
                    contentDescription = null
                )
            }
        } else if (errorType == 2 && !possibleText) {
            Box(
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.padding_18),
                    top = dimensionResource(id = R.dimen.padding_8)
                )
            ) {
                // 155 21
                Image(
                    painter = painterResource(id = R.drawable.nickname_error_2),
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.size_155))
                        .height(dimensionResource(id = R.dimen.padding_21)),
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