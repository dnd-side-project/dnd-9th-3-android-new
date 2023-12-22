package com.dnd_9th_3_android.gooding.record.tabMain.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.record.state.RecordState
import com.dnd_9th_3_android.gooding.record.state.rememberRecordState
import com.dnd_9th_3_android.gooding.record.tabMain.item.ItemCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.dnd_9th_3_android.gooding.core.data.R

@Composable
fun CalendarBottomSheet(
    bottomSheetState: MutableState<Boolean>,
    scope: CoroutineScope,
    recordState: RecordState
) {
    val buttonClickedState = remember { mutableStateOf(false) }
    val buttonColorState = animateColorAsState(
        targetValue =
        if ((!recordState.checkCategoryText()) && buttonClickedState.value) Color(0xBF3CEFA3)
        else if (!recordState.checkCategoryText()) Color(0xFF3CEFA3)
        else Color(0xFF3E4049)
    )
    val buttonTextColorState = animateColorAsState(
        targetValue =
        if (!recordState.checkCategoryText()) Color.Black
        else Color(0xFFA4A6AA)
    )

    Column(
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp
                )
            )
            .fillMaxWidth()
            .height(475.dp)
            .background(color = Color(0xFF282932))
    ) {
        Spacer(modifier = Modifier.height(36.dp))

        Column(modifier = Modifier.padding(horizontal = 18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "활동 날짜 선택",
                    fontFamily = pretendardBold,
                    fontSize = 16.sp,
                    letterSpacing = (-0.25).sp,
                    color = Color.White
                )

                Icon(
                    modifier = Modifier.clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) {
                        scope.launch {
                            recordState.recordDate.value = ""
                            bottomSheetState.value = false
                        }
                    },
                    painter = painterResource(id = R.drawable.close_my),
                    tint = Color.White,
                    contentDescription = "close_icon"
                )
            }

            Spacer(modifier = Modifier.height(26.dp))

            // calendar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp)
                    .height(278.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        modifier = Modifier
                            .rotate(180f)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) {
                                scope.launch {
                                    recordState.recordDate.value = ""
                                    bottomSheetState.value = false
                                }
                            },
                        painter = painterResource(id = R.drawable.arrow_right),
                        tint = Color.White,
                        contentDescription = "close_icon"
                    )

                    Text(
                        text = "현재 달 ",
                        fontFamily = pretendardBold,
                        fontSize = 16.sp,
                        letterSpacing = (-0.25).sp,
                        color = Color.White
                    )

                    Icon(
                        modifier = Modifier.clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            scope.launch {
                                recordState.recordDate.value = ""
                                bottomSheetState.value = false
                            }
                        },
                        painter = painterResource(id = R.drawable.arrow_right),
                        tint = Color.White,
                        contentDescription = "close_icon"
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    horizontalArrangement = Arrangement.spacedBy(28.dp)
                ) {
                    items(listOf("일", "월", "화", "수", "목", "금", "토")) {
                        Text(
                            text = it,
                            fontSize = 12.sp,
                            color = colorResource(id = R.color.blue_gray_3),
                            fontFamily = pretendardBold,
                            modifier = Modifier.wrapContentSize()
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(36.dp))

        Box(
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .height(47.dp)
                .background(color = buttonColorState.value)
                .pointerInput(Unit) {
                    detectTapGestures {
                        scope.launch {
                            buttonClickedState.value = true
                            delay(100L)
                            buttonClickedState.value = false
                            bottomSheetState.value = false
                        }
                    }
                }
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "선택 완료",
                color = buttonTextColorState.value,
                fontFamily = pretendardBold,
                fontSize = 16.sp,
                letterSpacing = (-0.25).sp
            )
        }
    }
}


@Preview
@Composable
fun PreviewCalendar() {
    CalendarBottomSheet(
        bottomSheetState = mutableStateOf(false),
        scope = rememberCoroutineScope(),
        recordState = rememberRecordState()
    )
}