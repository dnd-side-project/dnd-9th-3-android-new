package com.dnd_9th_3_android.gooding.search.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.data.component.pretendardRegular
import com.dnd_9th_3_android.gooding.model.search.PopularData

@Composable
fun PopularItem(
    popularData: PopularData,
    clickData : () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = popularData.ranking.toString(),
            color = Color.White,
            fontFamily = pretendardBold,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = popularData.text,
            color = Color.White,
            fontFamily = pretendardRegular,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}