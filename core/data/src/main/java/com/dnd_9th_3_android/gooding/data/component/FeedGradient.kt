package com.dnd_9th_3_android.gooding.data.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import com.dnd_9th_3_android.gooding.core.data.R

@Composable
fun topGradient(): Brush {
    return Brush.verticalGradient(
        listOf(
            colorResource(id = R.color.top_gra_color_1),
            colorResource(id = R.color.top_gra_color_2)
        )
    )
}

@Composable
fun bottomGradient(): Brush {
    return Brush.verticalGradient(
        0.1f to   colorResource(id = R.color.bottom_gra_color_1),
        0.25f to  colorResource(id = R.color.bottom_gra_color_2),
        1.0f to   colorResource(id = R.color.bottom_gra_color_3),
    )
}