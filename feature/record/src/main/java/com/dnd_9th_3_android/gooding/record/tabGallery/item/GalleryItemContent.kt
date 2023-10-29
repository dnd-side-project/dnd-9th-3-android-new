package com.dnd_9th_3_android.gooding.record.tabGallery.item

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.dnd_9th_3_android.gooding.model.record.GalleryImage
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.record.tabGallery.SelectImageCount.MAX_IMAGE_COUNT
import com.dnd_9th_3_android.gooding.record.tabGallery.component.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel

@Composable
fun GalleryItemContent(
    galleryImage: GalleryImage,
    viewModel: RecordViewModel,
) {
    Box {
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
                .clickable {
                    if (galleryImage.isSelected) {
                        viewModel.removeSelectedImage(galleryImage.id)
                    } else {
                        viewModel.addSelectedImage(galleryImage)
                    }
                    galleryImage.isSelected = !galleryImage.isSelected
                },
            alpha = if (viewModel.selectedImageSize() == MAX_IMAGE_COUNT &&
                !galleryImage.isSelected) 0.5f else 1f,
            error = {
                FailImageLoad()
            }

        )

        if (galleryImage.isSelected){
            Box(
                modifier = Modifier
                    .background(colorResource(id = R.color.secondary_1))
                    .size(24.dp)
                    .align(Alignment.TopEnd),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = viewModel.getSelectNumber(galleryImage).toString(),
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontFamily =  pretendardBold
                )
            }
        }
    }
}