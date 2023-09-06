package com.dnd_9th_3_android.gooding.feed.itemFeed

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.contentLayout.pretendardBold
import com.dnd_9th_3_android.gooding.data.contentLayout.pretendardRegular
import com.dnd_9th_3_android.gooding.feed.itemFeed.midInfoFunction.ExtendBoxState

@Composable
fun MidInfoLayer(
    location : String,
    subject : String,
    content : String,
    extendState : Int, // 클릭 여부 관찰
    changeState : (Int)->Unit
) {
    var lineCount = 1 
    // for back ground
    Column(
        modifier = Modifier
            .padding(start = dimensionResource(id = R.dimen.padding_18))
            .height(ExtendBoxState(extendState))
            .width(dimensionResource(id = R.dimen.size_280))
//            .background(Color.White)
            .clickable {
                if (extendState == 1) {
                    changeState(lineCount)
                } else {
                    changeState(1)
                }
            }
    ){
        // location
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // location Info
            Row(
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.location_color),
                        RoundedCornerShape(dimensionResource(id = R.dimen.corner_35)),
                    )
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_4),
                        bottom = dimensionResource(id = R.dimen.padding_4),
                        start = dimensionResource(id = R.dimen.padding_8_5),
                        end = dimensionResource(id = R.dimen.padding_8_5)
                    )
                    .blur(3.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(dimensionResource(id = R.dimen.padding_10))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.location), contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_3)))

                Text(
                    text = location,
                    style = TextStyle(
                        shadow = Shadow(
                            color = colorResource(id = R.color.tab_shadow),
                            blurRadius = 20f
                        ),
                        color = Color.White,
                        fontSize = dimensionResource(id = R.dimen.text_12).value.sp,
                        fontFamily = pretendardBold,
                    )
                )

            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_17)))

        // subject
        Text(
            text = subject,
            color = Color.White,
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.size_280))
                .wrapContentHeight(),
            fontFamily = pretendardBold,
            fontSize = dimensionResource(id = R.dimen.text_14_sp).value.sp
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_4)))

        // content
        Text(
            text = content,
            color = Color.White,
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.size_280))
                .wrapContentHeight(),
            fontFamily = pretendardRegular,
            fontSize = dimensionResource(id = R.dimen.text_12).value.sp,
            overflow = if (extendState==1)TextOverflow.Ellipsis else TextOverflow.Visible,
        )

        // for count content
        Text(
            text = content,
            color = Color.Transparent,
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.size_280))
                .wrapContentHeight(),
            fontFamily = pretendardRegular,
            fontSize = dimensionResource(id = R.dimen.text_12).value.sp,
            // line count
            onTextLayout ={textLayoutResult: TextLayoutResult ->
                lineCount = textLayoutResult.lineCount
            },
        )
    }
}