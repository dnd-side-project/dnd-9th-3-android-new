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

@Composable
fun NickNameScreen(
    onStepChange : (Int) -> Unit,
    viewModel: LoginViewModel
) {
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

        val text = remember { mutableStateOf(TextFieldValue(viewModel.getUserName()))
        }
        var possibleText by remember {
            mutableStateOf(true)
        }

        var errorType by remember { //error 1 = 닉네임 , 2 = 글자수
            mutableStateOf(0)
        }

        text.value.text.apply {
            viewModel.setUserName(text.value.text)
            if (this.length > 15) { // 글자 수 초과
                possibleText = false
                errorType = 2
            } else if (this=="굳잉" || this=="gooding") {
                // 닉네임 중복
                possibleText = false
                errorType = 1
            } else {
                possibleText = true
            }
        }

        EditTextBox(
            text,
            possibleText,
            "닉네임 입력 (15자 이내)",
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

        if (possibleText && text.value.text.isNotEmpty()){
            onStepChange(1)
        }else if (!possibleText && text.value.text.isNotEmpty()){
            onStepChange(0)
        }


    }

}