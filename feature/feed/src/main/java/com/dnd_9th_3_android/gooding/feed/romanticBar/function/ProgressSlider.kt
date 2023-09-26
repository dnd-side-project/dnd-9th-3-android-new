package com.dnd_9th_3_android.gooding.feed.romanticBar

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp

import com.smarttoolfactory.slider.*
import com.dnd_9th_3_android.gooding.core.data.R
// https://github.com/SmartToolFactory/Compose-Colorful-Sliders
@Composable
fun ProgressSlider(
    currentValue : Float,
    changeValue : (
        value : Float,
        offset : Offset,
    ) -> Unit,
) {
    ColorfulIconSlider(
        value = currentValue,
        onValueChange = { value, Offset->
            changeValue(value,Offset)
        },
        valueRange = 0f..100f,
        colors =   MaterialSliderDefaults.materialColors(
            thumbColor = SliderBrushColor(
                color = Color.Transparent
            ),
            activeTrackColor = SliderBrushColor(
                color = Color.Transparent
            ),
            disabledActiveTickColor  = SliderBrushColor(
                color = Color.Transparent
            ),
            inactiveTrackColor =  SliderBrushColor(
                color = Color.Transparent
            ),
            disabledInactiveTickColor =  SliderBrushColor(
                color = Color.Transparent
            ),
            inactiveTickColor =  SliderBrushColor(
                color = Color.Transparent
            ),
            activeTickColor =  SliderBrushColor(
                color = Color.Transparent
            ),
            disabledActiveTrackColor =  SliderBrushColor(
                color = Color.Transparent
            ),
            disabledInactiveTrackColor =  SliderBrushColor(
                color = Color.Transparent
            ),
            disabledThumbColor =  SliderBrushColor(
                color = Color.Transparent
            ),

        ),
    ){
        // 스크롤 박스
        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .size(44.dp)
        )
    }
}

