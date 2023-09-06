package com.dnd_9th_3_android.gooding.my.emptyDataLayout

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.contentLayout.pretendardBold

@Composable
fun SaveFeedDefaultScreen() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(end = dimensionResource(id = R.dimen.padding_18))
    ){
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_80)))
        Text(
            text = "내 취향의 굳이데이 기록을 모아보세요.",
            fontSize = dimensionResource(id = R.dimen.text_14_sp).value.sp,
            fontFamily = pretendardBold,
            color = colorResource(id = R.color.blue_gray_3),
            modifier = Modifier.wrapContentSize()
        )
    }
}