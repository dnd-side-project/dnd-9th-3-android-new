package com.dnd_9th_3_android.gooding.feed.itemFeed

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.data.component.pretendardRegular
import com.dnd_9th_3_android.gooding.feed.itemFeed.midInfoFunction.ExtendBoxState


// refactoring
@Composable
fun ContentInfoLayer(
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
            .padding(
                start = 18.dp,
                end = 62.dp,
                bottom = 137.dp
            )
            .wrapContentHeight()
            .fillMaxWidth()
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
                        RoundedCornerShape(35.dp),
                    )
                    .padding(
                        top = 4.dp,
                        bottom = 4.dp,
                        start = 9.25.dp,
                        end = 8.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,

            ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.location), contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.width(3.dp))

                Text(
                    text = location,
                    style = TextStyle(
                        shadow = Shadow(
                            color = colorResource(id = R.color.tab_shadow),
                            blurRadius = 20f
                        ),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = pretendardBold,
                    )
                )

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // subject
        Text(
            text = subject,
            color = Color.White,
            modifier = Modifier
                .width(280.dp)
                .wrapContentHeight(),
            fontFamily = pretendardBold,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(4.dp))

        // content
        Box(modifier = Modifier.wrapContentSize()) {
            Text(
                text = content,
                color = Color.White,
                modifier = Modifier
                    .width(280.dp)
                    .wrapContentHeight(),
                fontFamily = pretendardRegular,
                fontSize = 12.sp,
                maxLines = extendState,
                overflow = if (extendState == 1) TextOverflow.Ellipsis else TextOverflow.Visible,
            )
            // for count content - 투명 박스
            Text(
                text = content,
                color = Color.Transparent,
                modifier = Modifier
                    .width(280.dp)
                    .wrapContentHeight(),
                fontFamily = pretendardRegular,
                fontSize = 12.sp,
                // line count
                onTextLayout ={textLayoutResult: TextLayoutResult ->
                    lineCount = textLayoutResult.lineCount
                },
            )
        }
    }

}