package com.dnd_9th_3_android.gooding.my.tabTop.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
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
import com.dnd_9th_3_android.gooding.data.preventScroll.disabledVerticalPointerInputScrollPost
import com.dnd_9th_3_android.gooding.data.preventScroll.disabledVerticalPointerInputScrollPrev
import com.dnd_9th_3_android.gooding.my.subLayout.LevelScreen
import com.dnd_9th_3_android.gooding.my.subLayout.TopMenuScreen
import com.dnd_9th_3_android.gooding.my.subLayout.UserInfoScreen
import com.dnd_9th_3_android.gooding.my.viewModel.MyOptionViewModel

// 클릭 이벤트를 위한 샘플 뷰

@Composable
fun MainTopClickScreen(
    goSetting:() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            // main content (top menu)
            Spacer(modifier = Modifier.height(53.dp))
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
                    fontSize = 18.sp,
                    fontFamily = pretendardBold,
                    letterSpacing = (-0.25).sp
                )
                Box(
                    Modifier.align(Alignment.CenterEnd)
                        .size(24.dp)
                        .clickable {
                            goSetting()
                        }
                )
            }
            Spacer(modifier = Modifier.height(36.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
            ) {
                Box(
                    modifier = Modifier
                        .height(147.02.dp)
                        .width(166.28.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                BoxText(
                    listOf(Color.Transparent, Color.Transparent),
                    RoundedCornerShape(6.dp),
                    Color.Transparent,
                    "text Transparent",
                    12.sp,
                    Color.Transparent,
                    6.dp,
                    10.dp,
                    (-0.25).sp
                )

            }
            Spacer(modifier = Modifier.height(27.98.dp))
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
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier =Modifier.width(10.dp))
                // user name
                Text(
                    text =  "username",
                    fontSize = 16.sp,
                    color = Color.Transparent,
                    fontFamily = pretendardBold,
                    letterSpacing = (-0.25).sp
                )
                Spacer(modifier = Modifier.weight(1f))
                // 프로필 수정 menu
                Box(modifier = Modifier
                    .wrapContentSize()
                    .clickable {
                        // go 프로필 수정 ~
                    }
                ) {
                    BoxText(
                        listOf(Color.Transparent, Color.Transparent),
                        RoundedCornerShape(4.dp),
                        Color.Transparent,
                        "프로필 수정",
                        12.sp,
                        Color.Transparent,
                        6.dp,
                        10.dp,
                        (-0.25).sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(27.dp))
        }
    }
}