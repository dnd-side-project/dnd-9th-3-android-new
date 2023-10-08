package com.dnd_9th_3_android.gooding.my.mainScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.BoxText
import com.dnd_9th_3_android.gooding.data.component.pretendardBold

@Composable
fun DefaultTimeLineScreen(
    goRecord : () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 18.dp,
                end = 18.dp,
                bottom = 27.dp
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "이번달의 첫번째 굳이데이 기록을 남겨보세요.",
            fontSize = 14.sp,
            fontFamily = pretendardBold,
            color = colorResource(id = R.color.blue_gray_3),
            letterSpacing = (-0.25).sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        //record button
        Box(
            modifier = Modifier.clickable { goRecord() }
        ){
            BoxText(
                borderColor = listOf(colorResource(id = R.color.blue_gray_3),colorResource(id = R.color.blue_gray_3)),
                borderShape = RoundedCornerShape(8.dp),
                borderBackground = Color.Transparent,
                text = "기록하기",
                fontSize = 16.sp,
                fontColor = Color.White ,
                hoPadding = 11.5.dp,
                verPadding = 0.dp,
                (-0.25).sp
            )
        }
    }
}