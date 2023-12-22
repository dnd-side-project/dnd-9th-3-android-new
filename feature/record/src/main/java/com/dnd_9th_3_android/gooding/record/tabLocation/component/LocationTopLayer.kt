package com.dnd_9th_3_android.gooding.record.tabLocation.component

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dnd_9th_3_android.gooding.core.data.R
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.component.pretendardBold

@Composable
fun LocationTopLayer(
    backState: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
            .padding(bottom = 14.dp,start = 11.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .size(24.dp)
                .clickable {
                    backState()
                },
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_back_24),
                contentDescription = null
            )
        }

        Text(
            text = "장소 선택",
            letterSpacing = (-2.5).sp,
            fontSize = 18.sp,
            color = Color.White,
            fontFamily = pretendardBold,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        Divider(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(1.dp)
                .background(color = colorResource(id = R.color.blue_gray_6))
        )
    }
}

@Preview
@Composable
fun PreviewLocationTopLayer(){
    LocationTopLayer(
        backState = {}
    )
}