package com.dnd_9th_3_android.gooding.feed.romanticBar

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.customProgress.CustomProgressBar

// 백 그라운드 뷰
@Composable
fun BarInternalContent() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Spacer(modifier = Modifier.width(18.dp))

        //start image
        Box(
            modifier = Modifier
                .size(24.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.bar_start_image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
            )   
        }

        Spacer(modifier = Modifier.width(16.dp))

        //bar
        CustomProgressBar(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .height(5.dp),
            width = 206.dp,
            backgroundColor = Color.Transparent,
            foregroundColor = Brush.horizontalGradient(
                listOf(colorResource(id = R.color.blue_gray_5),colorResource(id = R.color.blue_gray_5))
            ),
            percent = 100,
            isShownText = false
        )

        Spacer(modifier = Modifier.width(16.dp))

        Box(
            modifier = Modifier
                .size(24.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.bar_end_image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
    }
}