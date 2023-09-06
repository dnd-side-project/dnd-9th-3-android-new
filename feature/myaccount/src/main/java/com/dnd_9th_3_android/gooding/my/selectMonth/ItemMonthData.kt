package com.dnd_9th_3_android.gooding.my.selectMonth

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.model.month.MonthData
import com.dnd_9th_3_android.gooding.my.viewModel.TodayViewModel

@Composable
fun ItemMonthData(
    monthData : MonthData,
    onclick : (MonthData) -> Unit
) {
//
//    val changeColor by remember {
//        mutableStateOf(monthData.isSelected)
//    }
    val colorState by  animateColorAsState(
        if (monthData.isSelected) colorResource(id = R.color.secondary_1)
        else colorResource(id = R.color.blue_gray_3)
    )

    Box(
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.padding_8),
                bottom = dimensionResource(id = R.dimen.padding_8)
            )
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable {
                onclick(monthData)
            }
    ){
        Text(
            text = monthData.keyDate,
            color = colorState,
        )
    }
}