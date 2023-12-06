package com.dnd_9th_3_android.gooding.record.tabMain.item

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.dnd_9th_3_android.gooding.data.component.pretendardRegular
import com.dnd_9th_3_android.gooding.model.user.Category

@Composable
fun ItemCategory(
    category: Category,
    categoryState: MutableState<Int>
) {
    val borderColorState = animateColorAsState(
        targetValue =
        if (category.index == categoryState.value) Color(0xB33CEFA3)
        else Color(0xFF3E4049)
    )

    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(33.dp))
            .border(
                width = 2.dp,
                shape = RoundedCornerShape(33.dp),
                color = borderColorState.value
            )
            .height(35.dp)
            .background(color = Color(0xFF3E4049))
            .clickable { categoryState.value = category.index },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(6.dp))

        Image(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = category.imageResource),
            contentDescription = "category_image"
        )

        Spacer(modifier = Modifier.width(2.dp))

        Text(
            text = category.name,
            fontFamily = pretendardRegular,
            fontSize = 14.sp,
            letterSpacing = (-0.25).sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.width(14.dp))
    }
}