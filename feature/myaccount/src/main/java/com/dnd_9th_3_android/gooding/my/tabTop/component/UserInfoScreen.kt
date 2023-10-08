package com.dnd_9th_3_android.gooding.my.subLayout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import coil.annotation.ExperimentalCoilApi
import com.dnd_9th_3_android.gooding.core.data.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.component.BoxText
import com.dnd_9th_3_android.gooding.data.component.pretendardBold

// user info
@OptIn(ExperimentalCoilApi::class)
@Composable
fun UserInfoScreen() {
    // image
    // 수정 필요
//    val painter = rememberImagePainter(
//        data = com.dnd_9th_3_android.gooding.api.UserInfo.myData!!.profileImgUrl,
//        builder = {
//            crossfade(true)
//        }
//    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // user image
        Box(
            modifier = Modifier.size(32.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.profile_nomal),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
        Spacer(modifier =Modifier.width(10.dp))
        // user name
        Text(
            text =  "username",
            fontSize = 16.sp,
            color = Color.White,
            fontFamily = pretendardBold,
            letterSpacing = (-0.25).sp
        )
        Spacer(modifier = Modifier.weight(1f))
        // 프로필 수정 menu
        BoxText(
            listOf(colorResource(id = R.color.blue_gray),colorResource(id = R.color.blue_gray)),
            RoundedCornerShape(4.dp),
            colorResource(id = R.color.blue_gray),
            "프로필 수정",
            12.sp,
            Color.White,
            6.dp,
            10.dp,
            (-0.25).sp
        )
    }
}