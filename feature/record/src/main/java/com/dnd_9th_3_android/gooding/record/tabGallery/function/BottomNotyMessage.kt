package com.dnd_9th_3_android.gooding.record.tabGallery.function

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.component.pretendardRegular


@Composable
fun BottomNotyMessage (selected : Int){
    Text(
        text = if (selected>0)
                "영상은 초반 15초까지만 업로드 됩니다. "
            else
                "최대 5개의 이미지 및 영상을 선택할 수 있습니다.",
        color = Color.White,
        modifier = Modifier.padding(bottom = 11.dp),
        fontFamily = pretendardRegular,
        fontSize = 14.sp
    )
}