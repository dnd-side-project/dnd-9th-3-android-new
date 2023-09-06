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
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.contentLayout.BoxText

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
                .height(dimensionResource(id = R.dimen.level_image_h))
                .width(dimensionResource(id = R.dimen.level_image_w))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_16)))

        BoxText(
            listOf(colorResource(id = R.color.for_grada_1),colorResource(id = R.color.secondary_1)),
            RoundedCornerShape(dimensionResource(id = R.dimen.corner_6)),
            Color.Transparent,
            levelText,
            dimensionResource(id = R.dimen.text_12).value.sp,
            Color.White,
            dimensionResource(id = R.dimen.padding_6),
            dimensionResource(id = R.dimen.padding_10)
        )

    }
}
