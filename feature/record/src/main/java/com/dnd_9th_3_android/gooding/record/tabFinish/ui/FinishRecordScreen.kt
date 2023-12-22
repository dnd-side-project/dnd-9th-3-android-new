package com.dnd_9th_3_android.gooding.record.tabFinish

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.record.progress.ProgressBar
import com.dnd_9th_3_android.gooding.record.state.RecordState
import com.dnd_9th_3_android.gooding.record.state.rememberRecordState
import com.dnd_9th_3_android.gooding.record.tabFinish.item.ItemRomanticQuotient
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FinishRecordScreen(
    recordState: RecordState,
//    viewModel: RecordViewModel = hiltViewModel()
) {

    val appWidth = LocalConfiguration.current.screenWidthDp.dp / 3
    val scope = rememberCoroutineScope()
    val loadingState = remember { mutableStateOf(false) }
    val progressState = animateDpAsState(
        targetValue =
        if (loadingState.value) appWidth * 3
        else appWidth * 2
    )
    val buttonClickedState = remember { mutableStateOf(false) }
    val recordButtonColorState = animateColorAsState(
        targetValue =
        if (recordState.romanceLevelState.value != 0 && buttonClickedState.value) Color(0xBF3CEFA3)
        else if (recordState.romanceLevelState.value != 0) Color(0xFF3CEFA3)
        else Color(0xFF3E4049)
    )
    val recordButtonTextColorState = animateColorAsState(
        targetValue =
        if (recordState.romanceLevelState.value == 0) Color(0xFFA4A6AA)
        else Color(0xFF1C1D27)
    )

    Box(
        modifier = Modifier
            .background(color = Color(0xFF1C1D27))
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ProgressBar(
                backStage = {
//                    viewModel.prevStep()
                },
                progressState = progressState
            )

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

            LazyVerticalGrid(
                modifier = Modifier.padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                columns = GridCells.Fixed(2),
            ) {
                items(5) {
                    ItemRomanticQuotient(
                        currentLevel = it+1,
                        selectedLevel = recordState.romanceLevelState
                    )
                }
            }
        }

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
                            if (recordState.romanceLevelState.value != 0) {
                                loadingState.value = true
                                buttonClickedState.value = true
                                delay(100L)
                                buttonClickedState.value = false
                                // go result
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


@Preview
@Composable
fun PreviewFinishRecordScreen() {
    FinishRecordScreen(
        recordState = rememberRecordState()
    )
}