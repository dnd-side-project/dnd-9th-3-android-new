package com.dnd_9th_3_android.gooding.record.tabGallery.component.folderItem

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dnd_9th_3_android.gooding.model.record.ImageFolder
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.data.component.pretendardRegular

@Composable
fun ItemFolder(
    folder : Pair<String, ImageFolder?>
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(colorResource(id = R.color.image_back))
        ){
            SubcomposeAsyncImage(
                folder.second?.firstImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = if (folder.second?.firstImage==null) 0f else 1f
            )
        }
        
        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = folder.first,
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = pretendardBold
            )

            Text(
                text = folder.second?.imageCount.toString(),
                color = colorResource(id = R.color.blue_gray_4),
                fontSize = 14.sp,
                fontFamily = pretendardRegular
            )
        }
        
    }
}