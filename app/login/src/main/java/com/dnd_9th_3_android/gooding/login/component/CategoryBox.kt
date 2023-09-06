package com.dnd_9th_3_android.gooding.login.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import com.dnd_9th_3_android.gooding.core.data.R
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
//import com.dnd_9th_3_android.gooding.login.type.CategoryListType
import com.dnd_9th_3_android.gooding.model.user.Category

@Composable
fun CategoryBox(
    category : Category,
    oneWidth : Dp,
    clickData : (Boolean)->Unit
) {
    var isSelect by remember{
        mutableStateOf(category.selected)
    }

    Box(
        // 선택 이미지 적용
        modifier = if (isSelect){
            Modifier
                .aspectRatio(1f)
                .background(
                    color = colorResource(id = R.color.blue_gray_6),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_10))
                )
                .border(
                    border = BorderStroke(
                        width = dimensionResource(id = R.dimen.padding_1_5),
                        color = colorResource(id = R.color.secondary_1)
                    ),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_10))
                )
                .clickable {
                    isSelect = !isSelect
                    clickData(isSelect)
                }
        }else{
            Modifier
                .aspectRatio(1f)
                .background(
                    color = colorResource(id = R.color.blue_gray_6),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_10))
                )
                .clickable {
                    isSelect = !isSelect
                    clickData(isSelect)
                }
        },
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_3))
        ){
            Box(modifier = Modifier
                .size(dimensionResource(id = R.dimen.size_50)),
            ){
                Image(
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = null,
                    painter = painterResource(id = category.imageResource),
                )
            }

            dimensionResource(id = R.dimen.size_5_37)

            Text(
                text = category.name,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = dimensionResource(id = R.dimen.text_12).value.sp,
            )

        }

    }

}