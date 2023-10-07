package com.dnd_9th_3_android.gooding.my.emptyDataLayer

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.pretendardBold

@Composable
fun SaveFeedDefaultScreen() {

    Box(
        modifier = Modifier
            .padding(top = 26.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "내 취향의 굳이데이 기록을 모아보세요.",
            fontSize = 14.sp,
            fontFamily = pretendardBold,
            color = colorResource(id = R.color.blue_gray_3)
        )
    }
}