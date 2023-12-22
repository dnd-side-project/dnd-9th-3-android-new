package com.dnd_9th_3_android.gooding.record.tabMain

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import com.dnd_9th_3_android.gooding.record.state.RecordState
import com.dnd_9th_3_android.gooding.record.tabMain.component.*
import com.holix.android.bottomsheetdialog.compose.BottomSheetBehaviorProperties
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialogProperties
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainRecordScreen(
    recordState: RecordState,
    viewModel: RecordViewModel = hiltViewModel()
) {
    val closeBottomSheetState = remember { mutableStateOf(false) }

    // 뒤로가기 제어 : 갤러리
    BackHandler(
        enabled = true,
        onBack = {
            closeBottomSheetState.value = true
        }
    )

    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val buttonClickedState = remember { mutableStateOf(false) }
    val calendarBottomSheetState = remember { mutableStateOf(false) }
    val categoryBottomSheetState = remember { mutableStateOf(false) }
    val nextButtonColorState = animateColorAsState(
        targetValue =
        if (recordState.checkNextStep() && buttonClickedState.value) Color(0xBF3CEFA3)
        else if (recordState.checkNextStep()) Color(0xFF3CEFA3)
        else Color(0xFF3E4049)
    )
    val nextButtonTextColorState = animateColorAsState(
        targetValue =
        if (recordState.checkNextStep()) Color(0xFF1C1D27)
        else Color(0xFFA4A6AA)
    )

    Box(
        modifier = Modifier
            .padding(top = 56.dp)
            .fillMaxSize()
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) {
                focusManager.clearFocus()
            }
    ) {
        CompositionLocalProvider(LocalOverscrollConfiguration.provides(null)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(36.dp))
                ImageLayer(
                    viewModel.selectedImages,
                    viewModel.selectedImageSize(),
                    removeImage = {
                        viewModel.removeSelectedImage(it)
                    },
                    addImage = {
                        viewModel.nextStep(ScreenRoot.SUB_GALLERY)
                    }
                )
                Spacer(modifier = Modifier.height(36.dp))
                MainLayer(
                    recordState,
                    focusManager,
                    onClickSearchLocation = {
                        viewModel.nextStep(ScreenRoot.LOCATION)
                    },
                    onClickSetCategory = {
                        categoryBottomSheetState.value = true
                    },
                    onClickShowCalendar = {
                        calendarBottomSheetState.value = true
                    },
                )

                Spacer(modifier = Modifier.height(130.dp))
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(121.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color(0x001C1D27),
                                Color(0xFF1C1D27)
                            )
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(91.dp)
                    .background(color = Color(0xFF1C1D27))
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 28.dp)
                        .padding(horizontal = 18.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .height(47.dp)
                        .background(color = nextButtonColorState.value)
                        .pointerInput(Unit) {
                            detectTapGestures {
                                scope.launch {
                                    buttonClickedState.value = true
                                    delay(100L)
                                    buttonClickedState.value = false
                                    // next step
                                }
                            }
                        }
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "다음",
                        color = nextButtonTextColorState.value,
                        fontFamily = pretendardBold,
                        fontSize = 16.sp,
                        letterSpacing = (-0.25).sp
                    )
                }
            }
        }
    }
    if (calendarBottomSheetState.value) {
        BottomSheetDialog(
            onDismissRequest = {
                calendarBottomSheetState.value = false
            },
            properties = BottomSheetDialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false,
                behaviorProperties = BottomSheetBehaviorProperties(
                    isDraggable = false
                )
            )
        ) {
            CalendarBottomSheet(
                bottomSheetState = calendarBottomSheetState,
                scope = scope,
                recordState = recordState
            )
        }
    }
    if (categoryBottomSheetState.value) {
        BottomSheetDialog(
            onDismissRequest = {
                categoryBottomSheetState.value = false
            },
            properties = BottomSheetDialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false,
                behaviorProperties = BottomSheetBehaviorProperties(
                    isDraggable = false
                )
            )
        ) {
            CategorySelectScreen(
                bottomSheetState = categoryBottomSheetState,
                scope = scope,
                recordState = recordState
            )
        }
    }
    if (closeBottomSheetState.value) {
        BottomSheetDialog(
            onDismissRequest = {
                closeBottomSheetState.value = false
            },
            properties = BottomSheetDialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false,
                behaviorProperties = BottomSheetBehaviorProperties(
                    isDraggable =  false
                )
            )
        ) {
            CloseBottomSheet(
                closeSheet = {
                    closeBottomSheetState.value = false
                },
                exitMain = {
                    viewModel.goMain()
                }
            )
        }
    }

}