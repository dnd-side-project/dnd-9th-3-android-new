package com.dnd_9th_3_android.gooding.feed.recommendFunction

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
@Composable
fun RecommendCard() {
    Card(
        shape = RoundedCornerShape(20.dp),
        backgroundColor = colorResource(id = R.color.blue_gray_6),
        border  = BorderStroke(1.dp, colorResource(id = R.color.blue_gray_5)),
        modifier = Modifier
            .fillMaxSize()
    ) { Text(text = "text", fontSize = 30.sp) }
}