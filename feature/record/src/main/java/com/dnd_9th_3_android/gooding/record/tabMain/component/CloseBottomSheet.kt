package com.dnd_9th_3_android.gooding.record.tabMain.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.dnd_9th_3_android.gooding.core.data.R
import kotlinx.coroutines.launch

@Composable
fun CloseBottomSheet(
    closeSheet: () -> Unit,
    exitMain: () -> Unit
) {

    val buttonClickedState = remember { mutableStateOf(false) }

    Surface(
        color = colorResource(id = R.color.blue_gray_6),
        modifier = Modifier
            .height(195.dp)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp
                )
            )
    ) {
        Box(
            modifier = Modifier
                .padding(
                    top = 36.dp,
                    start = 18.dp,
                    end = 13.dp,
                    bottom = 28.dp
                )
                .fillMaxWidth()

        ) {
            Box(
                modifier = Modifier
                    .height(48.dp)
                    .align(Alignment.TopStart)
            ) {
                Text(
                    text = "작성중인 내용이 초기화됩니다.",
                    modifier = Modifier.align(Alignment.TopStart),
                    fontSize = 16.sp,
                    fontFamily = pretendardBold,
                    color = Color.White,
                )
                Text(
                    text = "정말 나가시겠습니까?",
                    modifier = Modifier.align(Alignment.BottomStart),
                    fontSize = 16.sp,
                    fontFamily = pretendardBold,
                    color = Color.White
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.TopEnd)
                    .clickable {
                        closeSheet()
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.close_my),
                    contentDescription = null
                )
            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(47.dp)
                    .padding(end = 5.dp)
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = colorResource(id = R.color.blue_gray_3)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .align(Alignment.BottomCenter)
                    .clickable {
                        exitMain()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "나가기",
                    color = Color.White,
                    fontFamily = pretendardBold,
                    fontSize = 16.sp,
                    letterSpacing = (-0.25).sp,
                )
            }
        }
    }

}

@Preview
@Composable
fun PreviewCloseBottomSheet() {
    CloseBottomSheet(
        closeSheet = {},
        exitMain = {}
    )
}