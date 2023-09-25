package com.dnd_9th_3_android.gooding.data.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.dnd_9th_3_android.gooding.core.data.R
@Composable
fun BoxText(
    borderColor: List<Color>,
    borderShape: Shape,
    borderBackground: Color,
    text: String,
    fontSize: TextUnit,
    fontColor: Color,
    hoPadding : Dp,
    verPadding : Dp
) {
    val boxModifier =
        if (verPadding != 0.dp) Modifier
            .border(
                width = dimensionResource(id = R.dimen.border_size),
                shape = borderShape,
                brush = Brush.linearGradient(
                    colors = borderColor,
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
            .background(
                shape = borderShape,
                color = borderBackground
            )
        else Modifier.fillMaxWidth()
            .wrapContentHeight()
            .border(
                width = dimensionResource(id = R.dimen.border_size),
                shape = borderShape,
                brush = Brush.linearGradient(
                    colors = borderColor,
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
            .background(
                shape = borderShape,
                color = borderBackground
            )

    Box(
        contentAlignment = Alignment.Center,
        modifier = boxModifier
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            color = fontColor,
            fontFamily = pretendardBold,
            modifier = Modifier.padding(
                start = verPadding,
                end = verPadding,
                top = hoPadding,
                bottom = hoPadding
            )
        )
    }

}