package com.dnd_9th_3_android.gooding.my.itemFeed

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.dnd_9th_3_android.gooding.api.myApi.entity.MyRecordEntity
import com.dnd_9th_3_android.gooding.data.utils.video.CheckUrl
import com.dnd_9th_3_android.gooding.data.utils.video.VideoThumbnailUtil
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.data.component.pretendardRegular
import com.dnd_9th_3_android.gooding.data.utils.video.VideoDataChanger
import com.dnd_9th_3_android.gooding.model.feed.MyFeed

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CenterFeedLayout(
    feed : MyRecordEntity
) {
    // is video check
    val isVideo = CheckUrl.isVideo(feed.thumbnailUrl)
    val videoTime :String =
        if (isVideo) VideoDataChanger
            .getVideoTime(feed.thumbnailUrl)
        else "00"
    val painter = if (isVideo) rememberImagePainter(
        data = VideoThumbnailUtil.getWebVideoThumbnail(feed.thumbnailUrl),
        builder = { crossfade(true) }
    )
    else rememberImagePainter(
        data = feed.thumbnailUrl,
        builder = { crossfade(true) }
    )

    // location
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.map_pin),
            contentDescription = null,
            modifier = Modifier.size(12.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = feed.placeTitle,
            color = colorResource(id = R.color.secondary_1),
            fontSize = 14.sp,
            fontFamily = pretendardBold
        )
    }

    Spacer(modifier = Modifier.height(12.dp))

    // center image
    Box(
        modifier = Modifier.wrapContentSize()
    ) {
        Card(
            modifier = Modifier
                .height(333.dp)
                .width(218.dp)
                .align(Alignment.CenterStart),
            shape = RoundedCornerShape(4.dp),
            backgroundColor = Color.Transparent
        ){
            Box(modifier = Modifier.fillMaxSize()){
                Image(
                    painter = painter, contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                when (painter.state) {
                    is ImagePainter.State.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = colorResource(id = R.color.secondary_1)
                        )
                    }
                    else -> {}
                }
                if(isVideo) {
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(start = 12.dp, bottom = 11.dp)
                            .align(Alignment.BottomStart)
                    ){
                        Image(
                            painter= painterResource(id = R.drawable.play_button),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "00:${videoTime}",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontFamily = pretendardRegular
                        )
                    }
                }
            }
        }
        // 이미지 여러장인 경우 이미지 테스트 등록
        if (feed.files.size>1) {
            Text(
                modifier = Modifier
                    .align(Alignment.BottomEnd),
                text = "+${feed.files.size - 1}",
                color = colorResource(id = R.color.blue_gray_3),
                fontSize = 12.sp,
                fontFamily = pretendardBold
            )
        }
    }
}