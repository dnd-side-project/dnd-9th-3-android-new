package com.dnd_9th_3_android.gooding.record.tabFinish.item

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import com.dnd_9th_3_android.gooding.core.data.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.record.tabFinish.function.romanticIconState
import com.dnd_9th_3_android.gooding.record.tabFinish.function.romanticMessageState

@Composable
fun ItemRomanticQuotient(
    currentLevel : Int,
    selectedLevel : MutableState<Int>
) {
    val borderColorState = animateColorAsState(
        targetValue =
        if (currentLevel == selectedLevel.value) Color(0xFF3CEFA3)
        else Color(0xFF282932))

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(shape = RoundedCornerShape(11.dp))
            .border(
                width = (1.5).dp,
                color = borderColorState.value,
                shape = RoundedCornerShape(11.dp)
            )
            .background(color = Color(0xFF282932))
            .clickable {
                if (selectedLevel.value == currentLevel) selectedLevel.value = 0
                else selectedLevel.value = currentLevel
            }
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(75.dp),
                painter = painterResource(id = romanticIconState(currentLevel)),
                contentDescription = "romance_level_image"
            )

            Spacer(modifier = Modifier.height(17.dp))

            Text(
                text = romanticMessageState(level = currentLevel),
                color = Color(0xFF75777B),
                fontFamily = pretendardBold,
                fontSize = 14.sp,
                letterSpacing = (-0.25).sp
            )
        }
    }
}