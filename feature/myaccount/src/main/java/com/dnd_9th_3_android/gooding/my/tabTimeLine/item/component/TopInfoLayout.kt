package com.dnd_9th_3_android.gooding.my.itemFeed

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
import com.dnd_9th_3_android.gooding.data.utils.TimeDataChanger
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.BoxText
import com.dnd_9th_3_android.gooding.data.component.pretendardBold


@Composable
fun TopInfoLayout(
    timeData : String,
    onDelete : () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ){
        Text(
            text = TimeDataChanger().getRecordText(timeData),
            fontSize = 16.sp,
            fontFamily = pretendardBold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        // delete box
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .wrapContentSize()
                .clickable {
                    // delete
                    onDelete()
                }
        ){
            BoxText(
                borderColor = listOf(colorResource(id = R.color.blue_gray_3),colorResource(id = R.color.blue_gray_3)),
                borderShape = RoundedCornerShape(4.dp),
                borderBackground = Color.Transparent,
                text = "삭제",
                fontSize = 12.sp,
                fontColor =  colorResource(id = R.color.blue_gray_2),
                hoPadding = 6.dp,
                verPadding = 10.dp,
                0.sp
            )
        }
    }
}