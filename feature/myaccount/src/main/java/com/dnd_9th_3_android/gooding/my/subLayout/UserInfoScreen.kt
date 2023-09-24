package com.dnd_9th_3_android.gooding.my.subLayout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.dnd_9th_3_android.gooding.core.data.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.contentLayout.BoxText
import com.dnd_9th_3_android.gooding.data.contentLayout.pretendardBold

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
                start = dimensionResource(id = R.dimen.padding_20),
                end = dimensionResource(id = R.dimen.padding_20)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // user image
        Image(
            painter = painterResource(id = R.drawable.profile_nomal),
            contentDescription = null,
//            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.profile_image_size))
                .clip(CircleShape)
        )
        Spacer(modifier =Modifier.width(dimensionResource(id = R.dimen.padding_10)))
        // user name
        Text(
            text =  "username",
            fontSize = dimensionResource(id = R.dimen.text_16_sp).value.sp,
            color = Color.White,
            fontFamily = pretendardBold
        )
        Spacer(modifier = Modifier.weight(1f))
        // 프로필 수정 menu
        BoxText(
            listOf(colorResource(id = R.color.blue_gray),colorResource(id = R.color.blue_gray)),
            RoundedCornerShape(dimensionResource(id = R.dimen.corner_4)),
            colorResource(id = R.color.blue_gray),
            "프로필 수정",
            dimensionResource(id = R.dimen.text_12).value.sp,
            Color.White,
            dimensionResource(id = R.dimen.padding_6),
            dimensionResource(id = R.dimen.padding_10)
        )
    }
}