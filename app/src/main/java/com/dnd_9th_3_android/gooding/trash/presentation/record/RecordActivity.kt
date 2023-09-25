package com.dnd_9th_3_android.gooding.trash.presentation.record

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.MainActivity
import com.dnd_9th_3_android.gooding.R
import com.dnd_9th_3_android.gooding.data.sampleData.SampleRecordData
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.data.component.pretendardRegular
import com.dnd_9th_3_android.gooding.trash.presentation.gallery.GalleryFileUiData
import com.dnd_9th_3_android.gooding.trash.presentation.record.temp.BottomSheetDatePicker
import com.dnd_9th_3_android.gooding.ui.theme.GoodingTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

@AndroidEntryPoint
class RecordActivity : AppCompatActivity() {
    private val dateState = mutableStateOf<String?>(null)
    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.setFragmentResultListener(
            BottomSheetDatePicker.REQUEST_KEY,
            this
        ) { requestKey, bundle ->
            if (requestKey == BottomSheetDatePicker.REQUEST_KEY) {
                val date = bundle.getLong(BottomSheetDatePicker.Result.DATE)
                dateState.value = SimpleDateFormat("yy / MM / dd").format(date).toString()
            }
        }

        super.onCreate(savedInstanceState)

        setContent {
            GoodingTheme {
                RecordActivityContent(
                    showCalendar = {
                        val bottomSheetDatePicker = BottomSheetDatePicker.newInstance()
                        bottomSheetDatePicker.show(supportFragmentManager, bottomSheetDatePicker.tag)
                    },
                    goMain = {
                        SampleRecordData.sampleRecordData = SampleRecordData.newData
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        intent.putExtra("isOk",true)
                        startActivity(intent)
                    },
                    dateState,
                )
            }
        }
    }

    companion object {
        val CATEGORY_TEXT_MAP = HashMap<Int, String>().apply {
            set(1, "쇼핑")
            set(2, "여행")
            set(3, "미식")
            set(4, "독서")
            set(5, "요리")
            set(6, "문화")
            set(7, "스포츠")
            set(8, "취미")
            set(9, "학업")
            set(10, "이달의 굳이데이")
        }

        fun getIntent(context: Context, selectedFiles: List<GalleryFileUiData>): Intent {
            return Intent(context, RecordActivity::class.java).apply {
                putExtra(RecordViewModel.KEY_SELECTED_FILES, selectedFiles.toTypedArray())
            }
        }
    }
}

@Composable
private fun NewLocation(
    location : String,
    recordViewModel: RecordViewModel = hiltViewModel()
){
    recordViewModel.location.value = location
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun RecordActivityContent(
    showCalendar: () -> Unit,
    goMain: () ->Unit,
    dateState: MutableState<String?>,
    viewModel: RecordViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val phaseState = remember { mutableStateOf(0) }
    val unitWidth = LocalConfiguration.current.screenWidthDp.dp / 3
    val imageListState = remember { mutableStateListOf<Bitmap>() }
    val titleTextState = remember { mutableStateOf("") }
    val bodyTextState = remember { mutableStateOf("") }
    val categoryState = remember { mutableStateOf(0) }
    val currentLocationState = remember { mutableStateOf( viewModel.location.value) }
    val romanceLevelState = remember { mutableStateOf(0) }
    val progressWidthAsDpState = animateDpAsState(
        targetValue =
        if (phaseState.value == 1 && romanceLevelState.value != 0) unitWidth * 3
        else if (titleTextState.value.isNotEmpty() && bodyTextState.value.isNotEmpty() && dateState.value != null) unitWidth * 2
        else unitWidth
    )

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        scrimColor = Color(0x99000000),
        sheetBackgroundColor = Color.Transparent,
        sheetContent = {
            Column(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 24.dp,
                            topEnd = 24.dp
                        )
                    )
                    .fillMaxWidth()
                    .height(373.dp)
                    .background(color = Color(0xFF282932))
            ) {
                Spacer(modifier = Modifier.height(36.dp))

                Column(modifier = Modifier.padding(horizontal = 18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = SpaceBetween
                    ) {
                        Text(
                            text = "활동 카테고리 선택",
                            fontFamily = pretendardBold,
                            fontSize = 16.sp,
                            letterSpacing = (-0.25).sp,
                            color = Color.White
                        )

                        Icon(
                            modifier = Modifier.clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) { scope.launch { bottomSheetState.hide() } },
                            painter = painterResource(id = R.drawable.icon_close),
                            tint = Color.White,
                            contentDescription = "close_icon"
                        )
                    }

                    Spacer(modifier = Modifier.height(26.dp))

                    Row(horizontalArrangement = spacedBy(12.dp)) {
                        CategoryChip(
                            id = 1,
                            iconId = R.drawable.image_shopping,
                            text = "쇼핑",
                            categoryState = categoryState
                        )

                        CategoryChip(
                            id = 2,
                            iconId = R.drawable.image_travel,
                            text = "여행",
                            categoryState = categoryState
                        )

                        CategoryChip(
                            id = 3,
                            iconId = R.drawable.image_food,
                            text = "미식",
                            categoryState = categoryState
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(horizontalArrangement = spacedBy(12.dp)) {
                        CategoryChip(
                            id = 4,
                            iconId = R.drawable.image_book,
                            text = "독서",
                            categoryState = categoryState
                        )

                        CategoryChip(
                            id = 5,
                            iconId = R.drawable.image_cook,
                            text = "요리",
                            categoryState = categoryState
                        )

                        CategoryChip(
                            id = 6,
                            iconId = R.drawable.image_culture,
                            text = "문화",
                            categoryState = categoryState
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(horizontalArrangement = spacedBy(12.dp)) {
                        CategoryChip(
                            id = 7,
                            iconId = R.drawable.image_sports,
                            text = "스포츠",
                            categoryState = categoryState
                        )

                        CategoryChip(
                            id = 8,
                            iconId = R.drawable.image_hobby,
                            text = "취미",
                            categoryState = categoryState
                        )

                        CategoryChip(
                            id = 9,
                            iconId = R.drawable.image_study,
                            text = "학업",
                            categoryState = categoryState
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    CategoryChip(
                        id = 10,
                        iconId = R.drawable.image_monthly,
                        text = "이달의 굳이데이",
                        categoryState = categoryState
                    )
                }

                Spacer(modifier = Modifier.height(36.dp))

                val buttonClickedState = remember { mutableStateOf(false) }
                val buttonColorState = animateColorAsState(
                    targetValue =
                    if (categoryState.value != 0 && buttonClickedState.value) Color(0xBF3CEFA3)
                    else if (categoryState.value != 0) Color(0xFF3CEFA3)
                    else Color(0xFF3E4049)
                )
                val buttonTextColorState = animateColorAsState(targetValue = if (categoryState.value != 0) Color.Black else Color(0xFFA4A6AA))

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
                                    bottomSheetState.hide()
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
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF1C1D27))
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .align(CenterVertically)
                            .padding(start = 11.dp)
                            .size(24.dp),
                        painter = painterResource(id = R.drawable.icon_arrow_back),
                        tint = Color.White,
                        contentDescription = "icon_arrow_back"
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .background(color = Color(0xFF3E4049))
                ) {
                    Box(
                        modifier = Modifier
                            .width(progressWidthAsDpState.value)
                            .height(4.dp)
                            .background(color = Color(0xFF3CEFA3))
                    )
                }
            }

            when (phaseState.value) {
                0 -> {
                    RecordPhase1Screen(
                        imageListState,
                        titleTextState,
                        bodyTextState,
                        dateState,
                        categoryState,
                        onClickNext = { phaseState.value = 1 },
                        onClickShowCalendar = {
                            showCalendar()
                        },
                        onClickSetCategory = {
                            scope.launch {
                                if (bottomSheetState.currentValue == ModalBottomSheetValue.Hidden) {
                                    bottomSheetState.show()
                                } else {
                                    bottomSheetState.hide()
                                }
                            }
                        }
                    )
                }
                1 -> {
                    // 여기로
                    RecordPhase2Screen(
                        romanceLevelState,
                        onClickComplete = {
                            goMain()
                        }
                    )
                }
            }
        }
    }
}
@Composable
private fun CategoryChip(
    id: Int,
    iconId: Int,
    text: String,
    categoryState: MutableState<Int>
) {
    val borderColorState = animateColorAsState(targetValue = if (id == categoryState.value) Color(0xB33CEFA3) else Color(0xFF3E4049))

    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(33.dp))
            .border(
                width = 2.dp,
                shape = RoundedCornerShape(33.dp),
                color = borderColorState.value
            )
            .height(35.dp)
            .background(color = Color(0xFF3E4049))
            .clickable { categoryState.value = id },
        verticalAlignment = CenterVertically
    ) {
        Spacer(modifier = Modifier.width(6.dp))

        Image(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = iconId),
            contentDescription = "category_image"
        )

        Spacer(modifier = Modifier.width(2.dp))

        Text(
            text = text,
            fontFamily = pretendardRegular,
            fontSize = 14.sp,
            letterSpacing = (-0.25).sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.width(14.dp))
    }
}