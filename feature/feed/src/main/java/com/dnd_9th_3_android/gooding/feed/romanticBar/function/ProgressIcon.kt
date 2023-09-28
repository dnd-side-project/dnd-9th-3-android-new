package com.dnd_9th_3_android.gooding.feed.romanticBar.function

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.component.camptonBold
import com.dnd_9th_3_android.gooding.feed.romanticBar.MAX_COLOR_INDEX
import com.dnd_9th_3_android.gooding.feed.romanticBar.SCOPE_RATIO
import com.dnd_9th_3_android.gooding.feed.romanticBar.getRomanticColorList

// refactoring
// 상단 아이콘 ( 라벨 )
@Composable
fun ProgressGraphic(
    progress : Float,
    widthRatio : Dp
) {
    // color state
    val colorState by  animateColorAsState(
        if (progress==0.0f){
            colorResource(id = R.color.normal_state)
        }
        else if( (progress/ SCOPE_RATIO).toInt()>= MAX_COLOR_INDEX){
            getRomanticColorList()[MAX_COLOR_INDEX -1]
        }
        else {
            getRomanticColorList()[(progress / SCOPE_RATIO).toInt()]
        }
    )

//    // text modifier
//    val textModifier =
//        if (progress<10.0f){ // 한자리
//            Modifier
//                .fillMaxSize()
//                .padding(
//                    top = 6.dp,
//                    start = 14.dp
//                )
//        }else if (10.0f<=progress && progress< 100.0f){ //두자리
//            Modifier
//                .fillMaxSize()
//                .padding(
//                    top = 6.dp,
//                    start = 11.dp
//                )
//        }else{ //세자리
//            Modifier
//                .fillMaxSize()
//                .padding(
//                    top = 6.dp,
//                    start = 7.dp
//                )
//        }


    Row(
        modifier = Modifier
            .fillMaxSize(),
    ){
        // start scope
        Spacer(modifier = Modifier.width(widthRatio*44))

        // space box (투명 박스가 icon을 미는 형태)
        Box(
            modifier = Modifier
                .width(widthRatio * progress * 2)
                .background(Color.Transparent)
        )
        // icon
        Box(
            modifier = Modifier
                .width(widthRatio * 36)
                .height(64.dp)
        ) {

            // top image
            Box(
                modifier = Modifier
                    .height(31.dp)
                    .width(widthRatio * 36)
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
                    modifier = Modifier
                        .align(Alignment.Center)
                        .wrapContentWidth()
                        .padding(bottom = 1.dp)
                ) {
                    // progress text
                    Text(
                        text = progress.toInt().toString(),
                        fontSize = 12.sp,
                        fontFamily = camptonBold,
                        color = Color.White,
                        letterSpacing = (-0.25).sp,
                    )
                }
            }


            // bottom image
            Box(
                modifier = Modifier
                    .width(widthRatio * 28)
                    .height(28.dp)
                    .align(Alignment.BottomCenter),
                contentAlignment = Alignment.Center
            ) {
                //empty
                Image(
                    painterResource(id = R.drawable.circle_back_pro),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(26.dp)
                )
                //circle
                Image(
                    painter = painterResource(R.drawable.bottom_pro_image),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(26.dp)
                    ,
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
                        .padding(7.dp)
                        .align(Alignment.Center),
                    colorFilter = ColorFilter.tint(colorState)

                )
            }
        }


        // end scope
        Spacer(modifier = Modifier.width(widthRatio*44))
    }

}