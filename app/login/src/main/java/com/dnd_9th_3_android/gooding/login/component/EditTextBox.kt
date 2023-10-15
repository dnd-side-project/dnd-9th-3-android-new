package com.dnd_9th_3_android.gooding.login.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.dnd_9th_3_android.gooding.core.data.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.component.pretendardRegular
import androidx.compose.material.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditTextBox(
    text : MutableState<TextFieldValue>,
    possibleText : Boolean,
    hintText: String,
) {
    val cursorColor by animateColorAsState(
        if (possibleText){
            colorResource(id = R.color.secondary_1)
        }else{
            colorResource(id = R.color.warn_color)
        }
    )
    // Composable icon 생성 -> 입력 삭제 버튼
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                // text 삭제
                text.value = TextFieldValue("")
            }
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                ,contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.close_icon),
                    contentDescription = "",
                    Modifier
                        .background(colorResource(id = R.color.blue_gray_3), CircleShape)
                        .padding(5.dp)
                )
            }
        }
    }

    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    Surface( // 배경
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 18.dp,
                end = 18.dp,
            ),
        shape = RoundedCornerShape(6.dp),
        // 배경 칼라 적용
        border = if (text.value.text.isNotEmpty() && possibleText)
            BorderStroke(1.5.dp, colorResource(id = R.color.secondary_1_50)
        ) else if (text.value.text.isNotEmpty() && !possibleText)
            BorderStroke(1.5.dp, colorResource(id = R.color.warn_color))
        else null
        , color = colorResource(id = R.color.blue_gray_6)
    ) {
        BasicTextField(
            value = text.value,
            singleLine = true,
            textStyle = TextStyle(
                color = Color.White,
                fontSize =  14.sp,
                fontFamily =  pretendardRegular
            ),
            onValueChange = {text.value = it }, //값 변경 시 동작
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            cursorBrush  = SolidColor(cursorColor),
            interactionSource = interactionSource
        ){
            TextFieldDefaults.TextFieldDecorationBox(
                value = text.value.text,
                innerTextField = it,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                placeholder = {
                    Text(
                        text = hintText,
                        color = colorResource(id = R.color.blue_gray_3),
                        fontSize = 14.sp,
                        fontFamily = pretendardRegular,
                        letterSpacing = (-0.25).sp
                    )
                }, //힌트 (텍스트 ,칼라 적용 )
                trailingIcon = if (text.value.text.isNotEmpty()) trailingIconView else null,
                contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(14.dp),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
            )
        }
    }

}
