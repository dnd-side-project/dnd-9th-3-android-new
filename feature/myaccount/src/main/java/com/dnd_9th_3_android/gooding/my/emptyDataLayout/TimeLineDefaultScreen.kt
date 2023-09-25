package com.dnd_9th_3_android.gooding.my.mainLayout

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
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.BoxText
import com.dnd_9th_3_android.gooding.data.component.pretendardBold

@Composable
fun DefaultTimeLineScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(end = dimensionResource(id = R.dimen.padding_18)),
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_20)))
        Text(
            text = "이번달의 첫번째 굳이데이 기록을 남겨보세요.",
            fontSize = dimensionResource(id = R.dimen.text_14_sp).value.sp,
            fontFamily = pretendardBold,
            color = colorResource(id = R.color.blue_gray_3)
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_12)))
        //record button
        Box(
            modifier = Modifier.clickable {
                //go record activity
            }
        ){
            BoxText(
                borderColor = listOf(colorResource(id = R.color.blue_gray_3),colorResource(id = R.color.blue_gray_3)),
                borderShape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_8)),
                borderBackground = Color.Transparent,
                text = "기록하기",
                fontSize = dimensionResource(id = R.dimen.text_16_sp).value.sp,
                fontColor = Color.White ,
                hoPadding = dimensionResource(id = R.dimen.padding_11_5),
                verPadding = dimensionResource(id = R.dimen.zero)
            )
        }
    }
}