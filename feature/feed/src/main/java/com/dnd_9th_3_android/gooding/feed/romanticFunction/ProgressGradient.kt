package com.dnd_9th_3_android.gooding.feed.romanticFunction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.customProgress.CustomProgressBar

@Composable
fun ProgressGradient(
    progress : Float,
    offset: Offset,
) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        // start space
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_58)))

        // mid state bar
        CustomProgressBar(
            Modifier
                .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_24)))
                .height(dimensionResource(id = R.dimen.padding_8)),
            dimensionResource(id = R.dimen.size_208),
            backgroundColor = Color.Transparent,
            foregroundColor = Brush.horizontalGradient(
                listOf(
                    colorResource(id = R.color.for_grada_1),
                    colorResource(id = R.color.secondary_1)
                )
            ),
            percent = progress.toInt(),
            isShownText = false
        )
        // end space
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_58)))
    }

}