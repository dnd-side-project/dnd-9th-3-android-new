package com.dnd_9th_3_android.gooding.search.component


import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.contentLayout.pretendardRegular
import androidx.compose.foundation.Image
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchTextLayer(
    hintText : String,
    visualCloseIcon : MutableState<Boolean>,
    searchText : MutableState<TextFieldValue>,
    searchData : () -> Unit
) {
    // 텍스트 존재하면 delete up
    visualCloseIcon.value = searchText.value.text.isNotEmpty()

    // Composable icon
    val trailingIconView = @Composable{
        IconButton(onClick = {}) {
            Row (
                Modifier
                    .height(24.dp)
                    .padding(end = 8.dp)
            ){
                // close box
                if (visualCloseIcon.value) {
                    Box(
                        Modifier
                            .size(24.dp)
                            .clickable {
                                searchText.value = TextFieldValue("")
                                visualCloseIcon.value = false
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.search_data_delete_24),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }else{ Box(Modifier.size(24.dp)) }
                Spacer(modifier = Modifier.width(4.dp))
                // search box
                Box(
                    Modifier
                        .size(24.dp)
                        .clickable {
                            searchData()
                        }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_24),
                        contentDescription = null,
                        tint = colorResource(id = R.color.blue_gray_4)
                    )
                }
            }
        }

    }

    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    Row {
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(108.dp),
            color = colorResource(id = R.color.tab_background)
        ){
            BasicTextField(
                value = searchText.value,
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = pretendardRegular
                ),
                onValueChange = {searchText.value = it},
                modifier = Modifier.fillMaxSize(),
                interactionSource = interactionSource,
                cursorBrush = SolidColor(colorResource(id = R.color.secondary_1))
            ){
                TextFieldDefaults.TextFieldDecorationBox(
                    value = searchText.value.text,
                    innerTextField = it,
                    singleLine = true ,
                    enabled = true,
                    visualTransformation = VisualTransformation.None,
                    trailingIcon = trailingIconView,
                    placeholder = {
                        Text(
                            text = hintText,
                            color = colorResource(id = R.color.blue_gray_3),
                            fontSize = 14.sp,
                        )
                    },
                    interactionSource = interactionSource,
                    contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                        start = 12.dp, end = 8.dp, top = 4.dp,bottom = 4.dp
                    ),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                )
            }
        }
    }
}