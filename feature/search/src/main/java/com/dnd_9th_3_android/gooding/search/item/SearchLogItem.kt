package com.dnd_9th_3_android.gooding.search.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.pretendardRegular
import com.dnd_9th_3_android.gooding.model.search.SearchLog
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource

@Composable
fun RecentlyItem(
    logData : SearchLog,
    deleteData : () -> Unit,
    clickData : () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Color.Transparent, shape = RoundedCornerShape(24.dp))
            .border(
                BorderStroke(1.dp, colorResource(id = R.color.blue_gray_2)),
                RoundedCornerShape(24.dp)
            )
    ){
        Row(
           modifier = Modifier.padding(
               vertical = 4.5.dp,
               horizontal = 12.dp
           )
        ){
            Text(
                text = logData.text,
                fontFamily = pretendardRegular,
                fontSize = 14.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(2.dp))
            Box(modifier = Modifier.size(18.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.close_18),
                    contentDescription = null,
                )
            }
        }
    }
}