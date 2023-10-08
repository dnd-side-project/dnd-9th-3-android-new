package com.dnd_9th_3_android.gooding.my.itemFeed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.data.component.pretendardRegular
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun BottomFeedLayout(
    subject : String,
    content : String,
    onMoreInfo : () -> Unit
) {

    Text(
        text = subject,
        fontSize = 12.sp,
        fontFamily = pretendardBold,
        color = Color.White,
        modifier = Modifier
            .wrapContentHeight()
            .width(268.dp),
        overflow = TextOverflow.Ellipsis
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = content,
        fontSize = 12.sp,
        fontFamily = pretendardRegular,
        color = Color.White,
        maxLines = 2,
        modifier = Modifier
            .wrapContentHeight()
            .width(268.dp),
        overflow = TextOverflow.Ellipsis
    )
    Spacer(modifier = Modifier.height(12.dp))

    Row(
        modifier = Modifier
            .wrapContentSize()
            .clickable {
                onMoreInfo()
                // go detail
            },
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "더보기",
            fontFamily = pretendardBold,
            fontSize = 12.sp,
            color = colorResource(id = R.color.blue_gray_3)
        )
        Spacer(modifier = Modifier.width(5.5.dp))
        Image(
            painter = painterResource(id = R.drawable.left_side_mini),
            contentDescription = null,
        )
    }
}

//// location
//Row(
//verticalAlignment = Alignment.CenterVertically,
//modifier = Modifier.padding(
//start=18.dp, end = 18.dp
//)
//) {
//    // location Info
//    Row(
//        modifier = Modifier
//            .background(Color.Gray, RoundedCornerShape(8.dp))
//            .padding(8.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.baseline_location_on_24), contentDescription =null,
//            modifier = Modifier.size(20.dp)
//        )
//        Spacer(modifier = Modifier.width(3.dp))
//        Text(text = location, color = Color.White, fontSize = 10.sp)
//    }
//
//    Divider(modifier = Modifier.weight(1f))
//
//}