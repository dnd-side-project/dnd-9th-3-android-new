package com.dnd_9th_3_android.gooding.record.tabGallery.item

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.dnd_9th_3_android.gooding.model.record.GalleryImage
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.record.tabGallery.function.SelectImageCount.MAX_IMAGE_COUNT
import com.dnd_9th_3_android.gooding.record.tabGallery.component.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel

@Composable
fun GalleryItemContent(
    galleryImage: GalleryImage,
    isPreventSelectMessage : MutableState<Boolean>,
    viewModel: RecordViewModel,
) {
    val selectedSize = viewModel.selectedImageSize()
    val selectedIndex = viewModel.getSelectNumber(galleryImage)
    Box(Modifier.padding(1.dp)) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(galleryImage.uri)
                .crossfade(true)
                .build(),
            loading = {
                CircularProgressIndicator(fraction = 0.3f)
            },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(viewModel.recordStateRepository.appWidth / 3)
                .height(viewModel.recordStateRepository.imageHeight)
                .animateContentSize()
                .border(
                    if (selectedIndex != 0) 1.5.dp else (-1.5).dp,
                    colorResource(id = R.color.secondary_1),
                    RectangleShape
                )
                .clickable {
                    if (selectedIndex != 0) {
                        viewModel.removeSelectedImage(galleryImage.id)
                    } else {
                        if (selectedSize < MAX_IMAGE_COUNT) {
                            viewModel.addSelectedImage(galleryImage)
                        } else {
                            isPreventSelectMessage.value = true
                        }
                    }
                },
            alpha = if (selectedSize >= MAX_IMAGE_COUNT && selectedIndex == 0) 0.5f
                    else 1f,
            error = {
                FailImageLoad()
            }

        )

        if (selectedIndex != 0) {
            Box(
                modifier = Modifier
                    .background(colorResource(id = R.color.secondary_1))
                    .size(24.dp)
                    .align(Alignment.TopEnd),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = selectedIndex.toString(),
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontFamily = pretendardBold
                )
            }

            if (selectedIndex == 1){
                Box(
                    modifier = Modifier
                        .background(colorResource(id = R.color.secondary_1))
                        .width(37.dp)
                        .height(24.dp)
                        .align(Alignment.BottomStart),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "표지",
                        color = colorResource(id = R.color.blue_gray_7),
                        fontFamily = pretendardBold,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}