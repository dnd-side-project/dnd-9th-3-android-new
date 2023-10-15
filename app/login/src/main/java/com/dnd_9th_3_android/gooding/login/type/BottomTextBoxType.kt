package com.dnd_9th_3_android.gooding.login.type

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.colorResource
import com.dnd_9th_3_android.gooding.login.component.BottomTextBox
import com.dnd_9th_3_android.gooding.core.data.R
@Composable
fun BottomTextBoxType(
    nextStepButtonType : MutableState<Int>
) {
    when (nextStepButtonType.value) {
        1 -> { // 선택 가능
            BottomTextBox(
                "다음",
                colorResource(id = R.color.blue_gray_7),
                colorResource(id = R.color.secondary_1)
            )
        }
        2 -> { // 선택 시 애니메이션
            BottomTextBox(
                "다음",
                colorResource(id = R.color.blue_gray_6),
                colorResource(id = R.color.secondary_1_25)
            )
        }
        3 -> { // 다음 액티비티로 이동
            BottomTextBox(
                "굳잉 시작하기",
                colorResource(id = R.color.blue_gray_7),
                colorResource(id = R.color.secondary_1)
            )
        }
        4 -> { // 다음 액티비티 클릭 이벤트
            BottomTextBox(
                "굳잉 시작하기",
                colorResource(id = R.color.blue_gray_6),
                colorResource(id = R.color.secondary_1_25)
            )
        }
        5-> { //다음 액티비티로 실패
            BottomTextBox(
                text = "굳잉 시작하기" ,
                colorResource(id = R.color.blue_gray_2),
                colorResource(id = R.color.blue_gray_5)
            )
        }
        else -> { // 초기화면
            BottomTextBox(
                "다음",
                colorResource(id = R.color.blue_gray_2),
                colorResource(id = R.color.blue_gray_5)
            )
        }
    }
}