package com.dnd_9th_3_android.gooding.record.tabMain.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.data.component.pretendardRegular
import com.dnd_9th_3_android.gooding.record.state.RecordState
import com.dnd_9th_3_android.gooding.record.tabMain.function.textSelectionColor
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel
import com.dnd_9th_3_android.gooding.core.data.R

@Composable
fun MainLayer(
    recordState: RecordState,
    focusManager: FocusManager,
    onClickShowCalendar: () -> Unit,
    onClickSearchLocation: () -> Unit,
    onClickSetCategory: () -> Unit,
) {
    val titleFocusState = remember { mutableStateOf(false) }
    val titleBorderColorState = animateColorAsState(
        targetValue = if (titleFocusState.value) colorResource(id = R.color.secondary_1)
        else colorResource(id = R.color.blue_gray_6)
    )
    val bodyFocusState = remember { mutableStateOf(false) }
    val bodyBorderColorState = animateColorAsState(
        targetValue = if (titleFocusState.value) colorResource(id = R.color.secondary_1)
        else colorResource(id = R.color.blue_gray_6)
    )
    val togglePositionState =
        animateDpAsState(targetValue = if (recordState.toggleState.value) 17.dp else 0.dp)
    val togglePathColorState = animateColorAsState(
        targetValue = if (recordState.toggleState.value) colorResource(id = R.color.secondary_1)
        else colorResource(id = R.color.blue_gray_5)
    )

    Column(modifier = Modifier.padding(horizontal = 18.dp)) {

        Row {
            Text(
                text = "어떤 굳이데이를 보내셨나요?",
                fontFamily = pretendardBold,
                fontSize = 14.sp,
                letterSpacing = (-0.25).sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = "*",
                fontFamily = pretendardBold,
                fontSize = 14.sp,
                letterSpacing = (-0.25).sp,
                color = Color(0xFF3CEFA3)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(6.dp))
                .fillMaxWidth()
                .height(48.dp)
                .background(color = Color(0xFF282932))
                .border(
                    width = 1.5.dp,
                    shape = RoundedCornerShape(6.dp),
                    color = titleBorderColorState.value
                )
        ) {
            CompositionLocalProvider(LocalTextSelectionColors.provides(textSelectionColor())) {
                BasicTextField(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(
                            start = 14.dp,
                            end = 40.dp
                        )
                        .fillMaxWidth()
                        .onFocusChanged { titleFocusState.value = it.hasFocus },
                    value = recordState.subject.value,
                    onValueChange = {
                        if (it.length <= 20) {
                            recordState.typeSubject(it)
                        } else if (recordState.getSubjectSize() > it.length) {
                            recordState.typeSubject(it.take(20))
                        }
                    },
                    singleLine = true,
                    cursorBrush = SolidColor(colorResource(id = R.color.secondary_1)),
                    textStyle = TextStyle(
                        fontFamily = pretendardRegular,
                        fontSize = 14.sp,
                        letterSpacing = (-0.25).sp,
                        color = Color.White
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                )
            }

            if (recordState.checkSubjectEmpty()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 14.dp),
                    text = "제목을 입력해주세요.",
                    fontFamily = pretendardRegular,
                    fontSize = 14.sp,
                    letterSpacing = (-0.25).sp,
                    color = colorResource(id = R.color.blue_gray_3)
                )
            }

            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 14.dp),
                text = "${recordState.getSubjectSize()}/20",
                fontFamily = pretendardBold,
                letterSpacing = (-0.25).sp,
                color = colorResource(id = R.color.blue_gray_4),
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(6.dp))
                .fillMaxWidth()
                .height(136.dp)
                .background(color = colorResource(id = R.color.blue_gray_6))
                .border(
                    width = 1.5.dp,
                    shape = RoundedCornerShape(6.dp),
                    color = bodyBorderColorState.value
                )
        ) {
            CompositionLocalProvider(LocalTextSelectionColors.provides(textSelectionColor())) {
                BasicTextField(
                    modifier = Modifier
                        .padding(14.dp)
                        .height(108.dp)
                        .fillMaxWidth()
                        .onFocusChanged { bodyFocusState.value = it.hasFocus },
                    value = recordState.comment.value,
                    onValueChange = {
                        if (it.length <= 100) {
                            recordState.typeComment(it)
                        } else if (recordState.getCommentSize() > it.length) {
                            recordState.typeComment(it.take(100))
                        }
                    },
                    cursorBrush = SolidColor(colorResource(id = R.color.secondary_1)),
                    textStyle = TextStyle(
                        fontFamily = pretendardRegular,
                        fontSize = 14.sp,
                        letterSpacing = (-0.25).sp,
                        color = Color.White
                    )
                )
            }

            if (recordState.checkCommentEmpty()) {
                Text(
                    modifier = Modifier
                        .padding(
                            start = 14.dp,
                            top = 14.dp
                        ),
                    text = "굳이데이 활동과 계기에 대해 작성해주세요.",
                    fontFamily = pretendardRegular,
                    fontSize = 14.sp,
                    letterSpacing = (-0.25).sp,
                    color = colorResource(id = R.color.blue_gray_3)
                )
            }

            Text(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 13.dp, end = 14.dp),
                text = "${recordState.getCommentSize()}/100",
                fontFamily = pretendardBold,
                letterSpacing = (-0.25).sp,
                color = colorResource(R.color.blue_gray_4),
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row {
            Text(
                text = "활동 날짜",
                fontFamily = pretendardBold,
                fontSize = 14.sp,
                letterSpacing = (-0.25).sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = "*",
                fontFamily = pretendardBold,
                fontSize = 14.sp,
                letterSpacing = (-0.25).sp,
                color = Color(0xFF3CEFA3)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(6.dp))
                .fillMaxWidth()
                .height(48.dp)
                .background(color = colorResource(id = R.color.blue_gray_6))
                .clickable {
                    focusManager.clearFocus()
                    onClickShowCalendar()
                }
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        start = 14.dp,
                        end = 10.dp
                    )
                    .fillMaxWidth()
                    .height(48.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = recordState.checkDateEmpty() ?: "YY / MM / DD",
                    fontSize = 14.sp,
                    letterSpacing = (-0.25).sp,
                    color = if (recordState.checkDateEmpty() == null)
                        colorResource(id = R.color.blue_gray_3) else Color.White,
                    fontFamily = pretendardRegular
                )

                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.arrow_right),
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "활동 장소",
            fontFamily = pretendardBold,
            fontSize = 14.sp,
            letterSpacing = (-0.25).sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(6.dp))
                .fillMaxWidth()
                .height(48.dp)
                .background(color = Color(0xFF282932))
                .clickable {
                    onClickSearchLocation()
                }
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        start = 14.dp,
                        end = 10.dp
                    )
                    .fillMaxWidth()
                    .height(48.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (recordState.checkCurrentLocation()) {
                        Icon(
                            modifier = Modifier.size(17.dp),
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = "map_icon",
                            tint = colorResource(id = R.color.blue_gray_3)
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = recordState.setInitLocation(),
                        fontSize = 14.sp,
                        letterSpacing = (-0.25).sp,
                        color = Color(0xFF75777B),
                        fontFamily = pretendardRegular
                    )
                }

                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.arrow_right),
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "활동 카테고리",
            fontFamily = pretendardBold,
            fontSize = 14.sp,
            letterSpacing = (-0.25).sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(6.dp))
                .fillMaxWidth()
                .height(48.dp)
                .background(color = colorResource(id = R.color.blue_gray_6))
                .clickable { onClickSetCategory() }
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        start = 14.dp,
                        end = 10.dp
                    )
                    .fillMaxWidth()
                    .height(48.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = recordState.getCategoryText(),
                    fontSize = 14.sp,
                    letterSpacing = (-0.25).sp,
                    color = if (recordState.checkCategoryText()) colorResource(id = R.color.blue_gray_3) else Color.White,
                    fontFamily = pretendardRegular
                )

                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.arrow_right),
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(
                text = "공개 여부 설정",
                fontFamily = pretendardBold,
                fontSize = 14.sp,
                letterSpacing = (-0.25).sp,
                color = Color.White
            )

            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clip(shape = RoundedCornerShape(18.dp))
                    .width(45.dp)
                    .height(28.dp)
                    .background(togglePathColorState.value)
                    .clickable { recordState.setToggleState() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .padding(start = togglePositionState.value)
                        .clip(shape = CircleShape)
                        .size(24.dp)
                        .background(color = Color.White)
                ) {

                }
            }

            Text(
                modifier = Modifier.align(Alignment.BottomStart),
                text = "기록을 공개하면, 다른 사람들도 나의 기록을 볼 수 있습니다.",
                fontFamily = pretendardRegular,
                fontSize = 12.sp,
                letterSpacing = (-0.25).sp,
                color = colorResource(id = R.color.blue_gray_3)
            )
        }
    }
}