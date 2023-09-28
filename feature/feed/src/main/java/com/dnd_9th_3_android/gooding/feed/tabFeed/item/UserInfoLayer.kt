package com.dnd_9th_3_android.gooding.feed.itemFeed

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.model.user.UserInfo
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp

// refactoring
@OptIn(ExperimentalCoilApi::class)
@Composable
fun UserInfoLayer(userInfo: UserInfo) {
    // 임시 수정 해야댐
    val painter = rememberImagePainter(
        data = userInfo.profileImgUrl,
        builder = {
            crossfade(true)
        }
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 18.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .background(
                    Color.Transparent,
                    shape = CircleShape
                )
                .size(26.dp),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
            when(painter.state){
                is ImagePainter.State.Loading ->{
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = colorResource(id = R.color.secondary_1)
                    )
                }
                else->{
                    Image(
                        painter = painterResource(id = R.drawable.profile_nomal),
                        modifier = Modifier.size(26.dp),
                        contentDescription = null
                    )
                }
            }
        }


        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = userInfo.nickname,
            style = TextStyle(
                color = Color.White,
                shadow = Shadow(
                    color = colorResource(id = R.color.tab_shadow),
                    blurRadius = 10f
                ),
                fontSize = 14.sp,
                fontFamily = pretendardBold
            )
        )

    }
}