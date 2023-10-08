package com.dnd_9th_3_android.gooding.my.itemFeed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.my.type.RecordScoreType


@Composable
fun LeftBarLayout(
    recordScore : Int,
) {
    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(RecordScoreType(recordScore)),
            modifier = Modifier.size(26.dp),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(14.dp))

        Divider(
            modifier = Modifier
                .height(503.dp)
                .width(2.dp)
                .background(
                    color = colorResource(id = R.color.blue_gray_4),
                    shape = RoundedCornerShape(20.dp)
                )
                .align(Alignment.CenterHorizontally)
        )
    }
}