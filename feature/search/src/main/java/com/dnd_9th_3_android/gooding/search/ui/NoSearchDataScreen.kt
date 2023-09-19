package com.dnd_9th_3_android.gooding.search.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.dnd_9th_3_android.gooding.core.data.R
import androidx.compose.material.Text
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.contentLayout.pretendardBold

@Composable
fun NoSearchDataScreen() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(164.dp))
        Text(
            text = "검색 결과가 없습니다",
            color = colorResource(id = R.color.blue_gray_3),
            fontSize = 14.sp,
            fontFamily = pretendardBold
        )
    }
}