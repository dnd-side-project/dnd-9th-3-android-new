package com.dnd_9th_3_android.gooding.feed.romanticFunction

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.dnd_9th_3_android.gooding.core.data.R
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.contentLayout.camptonBold

@Composable
fun ProgressGraphic(
    progress : Float,
    offset: Offset,
) {
    // color state
    val colorState by  animateColorAsState(
        if (progress==0.0f){
            colorResource(id = R.color.normal_state)
        }
        else if( (progress/SCOPE_RATIO).toInt()>= MAX_COLOR_INDEX){
            getRomanticColorList()[MAX_COLOR_INDEX-1]
        }
        else {
            getRomanticColorList()[(progress /SCOPE_RATIO).toInt()]
        }
    )

    // text modifier
    val textModifier =
        if (progress<10.0f){
            Modifier
                .fillMaxSize()
                .padding(
                    top = dimensionResource(id = R.dimen.padding_6),
                    start = dimensionResource(id = R.dimen.padding_14)
                )
        }else if (10.0f<=progress && progress< 100.0f){
            Modifier
                .fillMaxSize()
                .padding(
                    top = dimensionResource(id = R.dimen.padding_6),
                    start = dimensionResource(id = R.dimen.padding_11)
                )
        }else{
            Modifier
                .fillMaxSize()
                .padding(
                    top = dimensionResource(id = R.dimen.padding_6),
                    start = dimensionResource(id = R.dimen.padding_7)
                )
        }


    Row(
        modifier = Modifier
            .fillMaxSize()

    ){
        // start scope
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_44)))

        // space box (투명 박스가 icon을 미는 형태) - max 204
        Box(
            modifier = Modifier
                .width((progress * 2).dp)
                .background(Color.Transparent)
        )
        // icon
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.padding_36))
                .height(dimensionResource(id = R.dimen.size_64))
        ) {

            // top image
            Box(
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.union_pro_h))
                    .width(dimensionResource(id = R.dimen.union_pro_w))
                    .align(Alignment.TopCenter)
            ) {
                // 말풍선 이미지
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.union_pro),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(colorState)
                )

                // 텍스트
                Box(
                    modifier = textModifier
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .height(dimensionResource(id = R.dimen.padding_14))
                    ) {
                        // progress text
                        Text(
                            text = progress.toInt().toString(),
                            fontSize = dimensionResource(id = R.dimen.text_12).value.sp,
                            fontFamily = camptonBold,
                            color = Color.White,
                        )
                    }
                }
            }


            // bottom image
            Box(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.bottom_pro_size))
                    .align(Alignment.BottomCenter),
                contentAlignment = Alignment.Center
            ) {
                //empty
                Image(
                    painterResource(id = R.drawable.circle_back_pro),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(dimensionResource(id = R.dimen.circle_pro_size))
                )
                //circle
                Image(
                    painter = painterResource(R.drawable.bottom_pro_image),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center),
                    colorFilter = ColorFilter.tint(colorState)
                )
                //main

                Image(
                    painter = when (progress.toInt()) {
                        in 0..19 -> {
                            painterResource(id = R.drawable.pro_1)
                        }
                        in 20..39 -> {
                            painterResource(R.drawable.pro_2)
                        }
                        in 40..59 -> {
                            painterResource(R.drawable.pro_3)
                        }
                        in 60..79 -> {
                            painterResource(R.drawable.pro_4)
                        }
                        else -> {
                            painterResource(R.drawable.pro_5)
                        }
                    }, contentDescription = null,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_7))
                        .align(Alignment.Center),
                    colorFilter = ColorFilter.tint(colorState)

                )
            }
        }


        // end scope
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_44)))
    }

}