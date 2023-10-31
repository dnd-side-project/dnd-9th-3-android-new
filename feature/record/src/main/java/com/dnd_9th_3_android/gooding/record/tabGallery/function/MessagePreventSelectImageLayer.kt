package com.dnd_9th_3_android.gooding.record.tabGallery.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.pretendardBold

@Composable
fun MessagePreventSelectImageLayer() {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(start = 17.dp, end = 16.dp)
            .background(
                color = colorResource(id = R.color.blue_gray_6),
                shape = RoundedCornerShape(15.dp)
            ),
        contentAlignment = Alignment.Center

    ){
        Text(
            text = "이미지 및 영상은 최대 5개까지 선택할 수 있습니다.",
            fontSize = 14.sp,
            fontFamily = pretendardBold,
            color = Color.White,
            modifier = Modifier.padding(
                vertical = 25.5.dp
            )
        )
    }
}