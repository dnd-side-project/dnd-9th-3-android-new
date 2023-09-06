package com.dnd_9th_3_android.gooding.data.customRowTab

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit

@Composable
fun CustomText (
    text : String,
    textShadow: Shadow,
    fontFamily: FontFamily,
    fontSize : TextUnit,
    fontColor : Color

){

    Text(
        text = text,
        style = TextStyle(
            fontSize = fontSize ,
            fontFamily = fontFamily,
            shadow = textShadow,
            color = fontColor
        )
    )
}