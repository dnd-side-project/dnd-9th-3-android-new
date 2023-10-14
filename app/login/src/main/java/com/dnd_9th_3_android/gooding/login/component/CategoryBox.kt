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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
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
                    shape = RoundedCornerShape(10.dp)
                )
                .border(
                    border = BorderStroke(
                        width = 1.5.dp, color = colorResource(id = R.color.secondary_1)
                    ),
                    shape = RoundedCornerShape(10.dp)
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
                    shape = RoundedCornerShape(10.dp)
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
            modifier = Modifier.padding(top = 2.62.dp)
        ){
            Box(modifier = Modifier
                .size(oneWidth / 2)
                .aspectRatio(1f),
            ){
                Image(
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = null,
                    painter = painterResource(id = category.imageResource),
                )
            }

            Spacer(modifier = Modifier.height(5.38.dp))

            Text(
                text = category.name,
                fontFamily = pretendardBold,
                color = Color.White,
                fontSize = 12.sp
            )

        }

    }

}