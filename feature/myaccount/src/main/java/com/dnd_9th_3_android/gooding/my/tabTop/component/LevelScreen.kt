package com.dnd_9th_3_android.gooding.my.subLayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.BoxText

// user level
@Composable
fun LevelScreen(
    painter: Painter,
    levelText : String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().wrapContentHeight()
    ) {
        Image(
            painter = painter, contentDescription = null,
            modifier = Modifier
                .height(147.02.dp)
                .width(166.28.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        BoxText(
            listOf(colorResource(id = R.color.for_grada_1),colorResource(id = R.color.secondary_1)),
            RoundedCornerShape(6.dp),
            Color.Transparent,
            levelText,
            12.sp,
            Color.White,
            6.dp,
            10.dp,
            0.sp
        )

    }
}
