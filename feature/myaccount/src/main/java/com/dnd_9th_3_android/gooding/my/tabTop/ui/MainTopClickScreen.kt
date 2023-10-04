package com.dnd_9th_3_android.gooding.my.tabTop.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.BoxText
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.my.subLayout.LevelScreen
import com.dnd_9th_3_android.gooding.my.subLayout.TopMenuScreen
import com.dnd_9th_3_android.gooding.my.subLayout.UserInfoScreen
import com.dnd_9th_3_android.gooding.my.viewModel.MyOptionViewModel

// 클릭 이벤트를 위한 샘플 뷰

@Composable
fun MainTopClickScreen(
    viewModel : MyOptionViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            // main content (top menu)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.top_margin)))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(end = 17.dp)
            ){
                Text(
                    text = "마이 굳잉",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Transparent,
                    fontSize = dimensionResource(R.dimen.main_text_sp).value.sp,
                    fontFamily = pretendardBold
                )
                Box(
                    Modifier.align(Alignment.CenterEnd)
                        .size(24.dp)
                        .clickable {
                            viewModel.naviToSetting()
                        }
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_36)))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
            ) {
                Box(
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen.level_image_h))
                        .width(dimensionResource(id = R.dimen.level_image_w))
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_16)))

                BoxText(
                    listOf(Color.Transparent, Color.Transparent),
                    RoundedCornerShape(dimensionResource(id = R.dimen.corner_6)),
                    Color.Transparent,
                    "text Transparent",
                    dimensionResource(id = R.dimen.text_12).value.sp,
                    Color.Transparent,
                    dimensionResource(id = R.dimen.padding_6),
                    dimensionResource(id = R.dimen.padding_10)
                )

            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_28)))
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
                Box(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.profile_image_size))
                        .clip(CircleShape)
                )
                Spacer(modifier =Modifier.width(dimensionResource(id = R.dimen.padding_10)))
                // user name
                Text(
                    text =  "username",
                    fontSize = dimensionResource(id = R.dimen.text_16_sp).value.sp,
                    color = Color.Transparent,
                    fontFamily = pretendardBold
                )
                Spacer(modifier = Modifier.weight(1f))
                // 프로필 수정 menu
                BoxText(
                    listOf(Color.Transparent,Color.Transparent),
                    RoundedCornerShape(dimensionResource(id = R.dimen.corner_4)),
                    Color.Transparent,
                    "프로필 수정",
                    dimensionResource(id = R.dimen.text_12).value.sp,
                    Color.Transparent,
                    dimensionResource(id = R.dimen.padding_6),
                    dimensionResource(id = R.dimen.padding_10)
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_24)))
        }
    }
}