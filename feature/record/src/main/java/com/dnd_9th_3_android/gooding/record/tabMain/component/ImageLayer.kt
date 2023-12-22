package com.dnd_9th_3_android.gooding.record.tabMain.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.dnd_9th_3_android.gooding.data.component.pretendardRegular
import com.dnd_9th_3_android.gooding.record.tabGallery.component.CircularProgressIndicator
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.camptonBold
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.model.record.GalleryImage

@Composable
fun ImageLayer(
    imageList: SnapshotStateList<GalleryImage>,
    imageSize: Int,
    removeImage: (id : Long)-> Unit,
    addImage: ()-> Unit
) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(horizontal = 18.dp)
    ) {
        itemsIndexed(imageList) { index, item ->
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(2.dp))
                    .width(118.dp)
                    .height(180.dp)
            ) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.uri)
                        .crossfade(true)
                        .build(),
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(2.dp))
                        .width(118.dp)
                        .height(180.dp),
                    loading = {
                        CircularProgressIndicator(fraction = 0.3f)
                    },
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )

                Icon(
                    modifier = Modifier
                        .padding(2.dp)
                        .align(Alignment.TopEnd)
                        .size(24.dp)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            if (imageSize>1) { // 표지 삭제 방지
                                removeImage(item.id)
                            }
                        },
                    painter = painterResource(id = R.drawable.close_my),
                    tint = Color.White,
                    contentDescription = "close_icon"
                )

                if (index == 0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp)
                            .clip(shape = RoundedCornerShape(2.dp))
                            .width(31.dp)
                            .height(19.dp)
                            .background(color = Color(0xFF3CEFA3)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "표지",
                            fontFamily = camptonBold,
                            fontSize = 10.sp,
                        )
                    }
                }
            }
        }
        if (imageSize < 5){
            item(1){
                Box(
                    modifier = Modifier
                        .border(
                            shape = RoundedCornerShape(2.dp),
                            color = colorResource(id = R.color.blue_gray_4),
                            width = 1.5.dp
                        )
                        .width(118.dp)
                        .height(180.dp)
                        .clickable {
                            addImage()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.plus_circle),
                            contentDescription = null)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "사진 추가",
                            color = colorResource(id = R.color.blue_gray_4),
                            fontFamily = pretendardBold,
                            fontSize = 12.sp,
                        )
                    }
                }
            }
        }
    }
}