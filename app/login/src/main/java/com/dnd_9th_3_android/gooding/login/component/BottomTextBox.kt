package com.dnd_9th_3_android.gooding.login.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.pretendardBold

@Composable
fun BottomTextBox(
    text : String,
    textColor : Color,
    backGroundColor : Color,
) {
    Box(
        modifier = Modifier
            .background(
                color = backGroundColor,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_8))
            )
            .width(dimensionResource(id = R.dimen.size_324))
            .height(dimensionResource(id = R.dimen.size_47)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text =text,
            color = textColor,
            fontSize = dimensionResource(id = R.dimen.text_16_sp).value.sp,
            fontFamily = pretendardBold
        )
    }
}