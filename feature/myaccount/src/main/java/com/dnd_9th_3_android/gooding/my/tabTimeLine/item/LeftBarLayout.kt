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
        modifier = Modifier.padding(
            top = dimensionResource(id = R.dimen.border_size_2), // 2+12 = 14
            end = dimensionResource(id = R.dimen.padding_16)
        )
    ) {
        // 나중에 수정 - 이미지 낭만 지수에 따라서 변화
        Image(
            painter = painterResource(RecordScoreType(recordScore)),
            modifier = Modifier.size(dimensionResource(id = R.dimen.size_26)),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_14)))
        // padding 제외하고 1 weight
        Divider(
            modifier = Modifier
                .height(503.dp)
                .width(dimensionResource(id = R.dimen.border_size_2))
                .background(
                    color = colorResource(id = R.color.blue_gray_4),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_20))
                )
                .align(Alignment.CenterHorizontally)
        )
    }
}