package com.dnd_9th_3_android.gooding.presentation.record

import androidx.activity.ComponentActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.R
import com.dnd_9th_3_android.gooding.data.contentLayout.pretendardBold
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
inline fun RecordPhase2Screen(
    romanceLevelState: MutableState<Int>,
    crossinline onClickComplete: () -> Unit,
    viewModel: RecordViewModel = hiltViewModel()
) {
    val context = LocalContext.current as ComponentActivity
    val scope = rememberCoroutineScope()
    val loadingState = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(top = 56.dp)
            .fillMaxSize()
            .background(color = Color(0xFF1C1D27))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier
                    .padding(
                        top = 36.dp,
                        start = 18.dp
                    ),
                text = "작성하신 굳이데이는 얼마나 낭만적이었나요?",
                color = Color.White,
                fontFamily = pretendardBold,
                fontSize = 18.sp,
                letterSpacing = (-0.25).sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    RomanceLevel(
                        level = 1,
                        selectedLevel = romanceLevelState
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    RomanceLevel(
                        level = 2,
                        selectedLevel = romanceLevelState
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    RomanceLevel(
                        level = 3,
                        selectedLevel = romanceLevelState
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    RomanceLevel(
                        level = 4,
                        selectedLevel = romanceLevelState
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    RomanceLevel(
                        level = 5,
                        selectedLevel = romanceLevelState
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Box(modifier = Modifier.weight(1f))
                }
            }
        }

        val buttonClickedState = remember { mutableStateOf(false) }
        val recordButtonColorState = animateColorAsState(targetValue = if (romanceLevelState.value != 0 && buttonClickedState.value) Color(0xBF3CEFA3) else if (romanceLevelState.value != 0) Color(0xFF3CEFA3) else Color(0xFF3E4049))
        val recordButtonTextColorState = animateColorAsState(targetValue = if (romanceLevelState.value == 0) Color(0xFFA4A6AA) else Color(0xFF1C1D27))

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    horizontal = 18.dp,
                    vertical = 28.dp
                )
                .clip(shape = RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .height(47.dp)
                .background(color = recordButtonColorState.value)
                .pointerInput(Unit) {
                    detectTapGestures {
                        scope.launch {
                            if (romanceLevelState.value != 0) {
                                loadingState.value = true
                                buttonClickedState.value = true
                                delay(100L)
                                buttonClickedState.value = false
                                onClickComplete()
                            }
                        }
                    }
                }
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "기록 완료",
                color = recordButtonTextColorState.value,
                fontFamily = pretendardBold,
                fontSize = 16.sp,
                letterSpacing = (-0.25).sp
            )
        }

        if (loadingState.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0x50000000))
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) { }
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(0xFF3CEFA3)
                )
            }
        }
    }
}

@Composable
fun RowScope.RomanceLevel(
    modifier: Modifier = Modifier,
    level: Int,
    selectedLevel: MutableState<Int>
) {
    val borderColorState = animateColorAsState(targetValue = if (level == selectedLevel.value) Color(0xFF3CEFA3) else Color(0xFF282932))

    Box(
        modifier = modifier
            .weight(1f)
            .aspectRatio(1f)
            .clip(shape = RoundedCornerShape(11.dp))
            .border(
                width = (1.5).dp,
                color = borderColorState.value,
                shape = RoundedCornerShape(11.dp)
            )
            .background(color = Color(0xFF282932))
            .clickable { if (selectedLevel.value == level) selectedLevel.value = 0 else selectedLevel.value = level }
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(75.dp),
                painter = painterResource(
                    id = when (level) {
                        1 -> R.drawable.image_romance_1
                        2 -> R.drawable.image_romance_2
                        3 -> R.drawable.image_romance_3
                        4 -> R.drawable.image_romance_4
                        else -> R.drawable.image_romance_5
                    }
                ),
                contentDescription = "romance_level_image"
            )

            Spacer(modifier = Modifier.height(17.dp))

            Text(
                text = when (level) {
                    1 -> "낭만이긴 했어요"
                    2 -> "소소한 낭만이었어요"
                    3 -> "나름 낭만있었어요"
                    4 -> "낭만 그 자체였어요"
                    else -> "잊지못할 낭만이었어요"
                },
                color = Color(0xFF75777B),
                fontFamily = pretendardBold,
                fontSize = 14.sp,
                letterSpacing = (-0.25).sp
            )
        }
    }
}