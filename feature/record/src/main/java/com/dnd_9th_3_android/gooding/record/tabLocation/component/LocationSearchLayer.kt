package com.dnd_9th_3_android.gooding.record.tabLocation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import com.dnd_9th_3_android.gooding.core.data.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.component.pretendardRegular

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LocationSearchLayer(
    query: MutableState<TextFieldValue>
) {
    val iconState = @Composable {
        Box(modifier = Modifier
            .width(60.dp)
            .height(24.dp)
            .padding(end = 8.dp)
        ){
            if (query.value.text.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterStart)
                        .clickable {
                            query.value = TextFieldValue("")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.close_search_icon_24),
                        contentDescription = null
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.search_24),
                    contentDescription = null
                )
            }
        }
    }

    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(
                horizontal = 18.dp,
            ),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = query.value,
            singleLine = true,
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = pretendardRegular
            ),
            onValueChange = { query.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .background(
                    colorResource(id = R.color.blue_gray_6),
                    shape = RoundedCornerShape(108.dp)
                ),
            interactionSource = interactionSource,
            cursorBrush = SolidColor(colorResource(id = R.color.secondary_1)),
        ) {
            TextFieldDefaults.TextFieldDecorationBox(
                value = query.value.text,
                innerTextField = it,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                placeholder = {
                    Text(
                        text = "지역,장소 검색",
                        color = colorResource(id = R.color.blue_gray_3),
                        fontSize = 14.sp,
                        fontFamily = pretendardRegular,
                    )
                },
                trailingIcon = iconState,
                contentPadding = PaddingValues(start = 12.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewLocationSearchLayer() {
    LocationSearchLayer(query = mutableStateOf(TextFieldValue("")))
}