package com.dnd_9th_3_android.gooding.record.tabGallery.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.dnd_9th_3_android.gooding.core.data.R
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.record.viewModel.RecordOptionViewModel
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel

@Composable
fun GalleryTopLayer(
    optionViewModel : RecordOptionViewModel = hiltViewModel(),
    recordViewModel: RecordViewModel = hiltViewModel()
) {
    Box(
       modifier = Modifier
           .fillMaxWidth()
           .padding(
               start = 13.dp,
               bottom = 15.dp,
               end = 18.dp
           )
           .height(95.dp),
        contentAlignment = Alignment.BottomCenter
    ){
        Box(
            modifier = Modifier
                .clickable {
                    optionViewModel.goBackStage()
                }
                .size(24.dp)
                .align(Alignment.CenterStart),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.close_my),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
                .clickable {
                    // 폴더 리스트 + image 회전 (180도)
                }
        ){
            Text(
                text = recordViewModel.currentFolder.value.first,
                letterSpacing = (-0.25).sp,
                fontSize = 18.sp,
                fontFamily = pretendardBold,
                color = Color.White
            )
            Box(
                modifier = Modifier
                    .size(24.dp),
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.arrow_bottom),
                    contentDescription = null ,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable {
                    // 클릭 가능 상태면 이동, 다음 텍스트 변경
                    optionViewModel.goMainRecord()
                }
        ){
            Text(
                text = "다음",
                color = colorResource(id = R.color.blue_gray_3),
                letterSpacing = (-0.25).sp,
                fontFamily = pretendardBold,
                fontSize = 18.sp
            )
        }

   }
}