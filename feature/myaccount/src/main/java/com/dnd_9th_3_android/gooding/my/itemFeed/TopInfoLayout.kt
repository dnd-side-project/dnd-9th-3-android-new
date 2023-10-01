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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = TimeDataChanger().getRecordText(timeData),
            fontSize = dimensionResource(id = R.dimen.text_16_sp).value.sp,
            fontFamily = pretendardBold,
            color = Color.White
        )
        Spacer(modifier = Modifier.weight(1f))
        // delete box
        Box(
            modifier = Modifier
                .padding(end = dimensionResource(id = R.dimen.padding_18))
                .wrapContentSize()
                .clickable {
                    // delete
                    onDelete()
                }
        ){
            BoxText(
                borderColor = listOf(colorResource(id = R.color.blue_gray_3),colorResource(id = R.color.blue_gray_3)),
                borderShape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_4)),
                borderBackground = Color.Transparent,
                text = "삭제",
                fontSize = dimensionResource(id = R.dimen.text_12).value.sp,
                fontColor =  colorResource(id = R.color.blue_gray_2),
                hoPadding = dimensionResource(id = R.dimen.padding_6),
                verPadding = dimensionResource(id = R.dimen.padding_10)
            )
        }
    }
}