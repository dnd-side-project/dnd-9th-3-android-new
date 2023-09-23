package com.dnd_9th_3_android.gooding.trash.presentation.record

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.startActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.dnd_9th_3_android.gooding.R
import com.dnd_9th_3_android.gooding.data.contentLayout.pretendardBold
import com.dnd_9th_3_android.gooding.data.contentLayout.pretendardRegular
import com.dnd_9th_3_android.gooding.presentation.record.search.SearchGoodieDayPlaceActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalCoilApi::class)
@Composable
fun RecordPhase1Screen(
    imageListState: SnapshotStateList<Bitmap>,
    titleTextState: MutableState<String>,
    bodyTextState: MutableState<String>,
    dateState: MutableState<String?>,
    categoryState: MutableState<Int>,
    onClickNext: () -> Unit,
    onClickShowCalendar: () -> Unit,
    onClickSetCategory: () -> Unit,
    viewModel: RecordViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var isClick by remember {
        mutableStateOf(false)
    }
    var currentLocationState by remember{
        mutableStateOf("카테고리를 선택해주세요.(선택)")
    }
    val scope = rememberCoroutineScope()
    if (isClick){
        scope.launch {
            delay(1000)
            currentLocationState  = "제주특별자치도 제주시 오동동 산 182"
        }
    }
    val focusManager = LocalFocusManager.current
    LaunchedEffect(Unit) {
        viewModel.imageListFlow.collect {
            it.forEach { uri ->
                BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri))?.let {
                    imageListState.add(it)
                }
            }
        }
    }

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

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = PaddingValues(horizontal = 18.dp)
                ) {
                    itemsIndexed(imageListState) { index, item ->
                        val painter = rememberImagePainter(
                            data = if (index==0) "https://dnd9th-3.s3.ap-northeast-2.amazonaws.com/images/6be88bdd-99b9-4c84-831c-e169f39af82f1317a38b-f6bb-48df-9ffd-3fc719c2e8f2.jpeg"
                                else "https://dnd9th-3.s3.ap-northeast-2.amazonaws.com/images/11db12e9-c072-43b9-92bc-c8c988692573c6fc209c-3bc6-457f-883b-70dce4a063e8.jpeg",
                            builder = { crossfade(true) }
                        )
                        Box(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(2.dp))
                                .width(118.dp)
                                .height(180.dp)
                        ) {
                            Image(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(2.dp))
                                    .width(118.dp)
                                    .height(180.dp),
                                painter = painter,
//                                painter = BitmapPainter(item.asImageBitmap()),
                                contentScale = ContentScale.Crop,
                                contentDescription = "user_added_image"
                            )

                            Icon(
                                modifier = Modifier
                                    .padding(2.dp)
                                    .align(TopEnd)
                                    .size(24.dp)
                                    .clickable(
                                        interactionSource = MutableInteractionSource(),
                                        indication = null
                                    ) {
                                        // todo : remove image
                                    },
                                painter = painterResource(id = R.drawable.icon_close_small),
                                tint = Color.White,
                                contentDescription = "close_icon"
                            )

                            if (index == 0) {
                                Box(
                                    modifier = Modifier
                                        .align(BottomStart)
                                        .padding(8.dp)
                                        .clip(shape = RoundedCornerShape(2.dp))
                                        .width(31.dp)
                                        .height(19.dp)
                                        .background(color = Color(0xFF3CEFA3)),
                                    contentAlignment = Center
                                ) {
                                    Text(
                                        modifier = Modifier.align(Center),
                                        text = "표지",
                                        fontFamily = pretendardRegular,
                                        fontSize = 10.sp,
//                                        letterSpacing = (-0.25).sp
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(36.dp))

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

                    val titleFocusState = remember { mutableStateOf(false) }
                    val titleBorderColorState = animateColorAsState(targetValue = if (titleFocusState.value) Color(0x803CEFA3) else Color(0xFF282932))

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
                                    .align(CenterStart)
                                    .padding(
                                        start = 14.dp,
                                        end = 40.dp
                                    )
                                    .fillMaxWidth()
                                    .onFocusChanged { titleFocusState.value = it.hasFocus },
                                value = titleTextState.value,
                                onValueChange = {
                                    if (it.length <= 20) {
                                        titleTextState.value = it
                                    } else if (titleTextState.value.length > it.length) {
                                        titleTextState.value = it.take(20)
                                    }
                                },
                                singleLine = true,
                                cursorBrush = SolidColor(Color(0xFF3CEFA3)),
                                textStyle = TextStyle(
                                    fontFamily = pretendardRegular,
                                    fontSize = 14.sp,
                                    letterSpacing = (-0.25).sp,
                                    color = Color.White
                                ),
                                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                            )
                        }

                        if (titleTextState.value.isEmpty()) {
                            Text(
                                modifier = Modifier
                                    .align(CenterStart)
                                    .padding(start = 14.dp),
                                text = "제목을 입력해주세요.",
                                fontFamily = pretendardRegular,
                                fontSize = 14.sp,
                                letterSpacing = (-0.25).sp,
                                color = Color(0xFF75777B)
                            )
                        }

                        Text(
                            modifier = Modifier
                                .align(CenterEnd)
                                .padding(end = 14.dp),
                            text = "${titleTextState.value.length}/20",
                            fontFamily = pretendardBold,
                            letterSpacing = (-0.25).sp,
                            color = Color(0xFF575860),
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    val bodyFocusState = remember { mutableStateOf(false) }
                    val bodyBorderColorState = animateColorAsState(targetValue = if (bodyFocusState.value) Color(0x803CEFA3) else Color(0xFF282932))

                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(6.dp))
                            .fillMaxWidth()
                            .height(136.dp)
                            .background(color = Color(0xFF282932))
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
                                value = bodyTextState.value,
                                onValueChange = {
                                    if (it.length <= 100) {
                                        bodyTextState.value = it
                                    } else if (bodyTextState.value.length > it.length) {
                                        bodyTextState.value = it.take(100)
                                    }
                                },
                                cursorBrush = SolidColor(Color(0xFF3CEFA3)),
                                textStyle = TextStyle(
                                    fontFamily = pretendardRegular,
                                    fontSize = 14.sp,
                                    letterSpacing = (-0.25).sp,
                                    color = Color.White
                                )
                            )
                        }

                        if (bodyTextState.value.isEmpty()) {
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
                                color = Color(0xFF75777B)
                            )
                        }

                        Text(
                            modifier = Modifier
                                .align(BottomEnd)
                                .padding(bottom = 13.dp, end = 14.dp),
                            text = "${bodyTextState.value.length}/100",
                            fontFamily = pretendardBold,
                            letterSpacing = (-0.25).sp,
                            color = Color(0xFF575860),
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
                            .background(color = Color(0xFF282932))
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
                            horizontalArrangement = SpaceBetween,
                            verticalAlignment = CenterVertically
                        ) {
                            Text(
                                text = dateState.value ?: "YY / MM / DD",
                                fontSize = 14.sp,
                                letterSpacing = (-0.25).sp,
                                color = if (dateState.value == null) Color(0xFF75777B) else Color.White,
                                fontFamily = pretendardRegular
                            )

                            Icon(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(id = R.drawable.icon_arrow_right),
                                tint = Color.White,
                                contentDescription = "icon_arrow_back"
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
                                context.startActivity(SearchGoodieDayPlaceActivity.getIntent(context))
                                isClick = true
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
                            horizontalArrangement = SpaceBetween,
                            verticalAlignment = CenterVertically
                        ) {
                            Row(verticalAlignment = CenterVertically) {
                                if (currentLocationState=="카테고리를 선택해주세요.(선택)") {
                                    Image(
                                        modifier = Modifier.size(17.dp),
                                        painter = painterResource(id = R.drawable.icon_map),
                                        contentDescription = "map_icon"
                                    )
                                }

                                Spacer(modifier = Modifier.width(4.dp))

                                Text(
                                    text = currentLocationState,
                                    fontSize = 14.sp,
                                    letterSpacing = (-0.25).sp,
                                    color = Color(0xFF75777B),
                                    fontFamily = pretendardRegular
                                )
                            }

                            Icon(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(id = R.drawable.icon_arrow_right),
                                tint = Color.White,
                                contentDescription = "icon_arrow_back"
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
                            .background(color = Color(0xFF282932))
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
                            horizontalArrangement = SpaceBetween,
                            verticalAlignment = CenterVertically
                        ) {
                            Text(
                                text = if (categoryState.value == 0) "카테고리를 선택해주세요. (선택)" else RecordActivity.CATEGORY_TEXT_MAP[categoryState.value]!!,
                                fontSize = 14.sp,
                                letterSpacing = (-0.25).sp,
                                color = if (categoryState.value == 0) Color(0xFF75777B) else Color.White,
                                fontFamily = pretendardRegular
                            )

                            Icon(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(id = R.drawable.icon_arrow_right),
                                tint = Color.White,
                                contentDescription = "icon_arrow_back"
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

                        val toggleState = remember { mutableStateOf(false) }
                        val togglePositionState = animateDpAsState(targetValue = if (toggleState.value) 17.dp else 0.dp)
                        val togglePathColorState = animateColorAsState(targetValue = if (toggleState.value) Color(0xFF3CEEA2) else Color(0xFF3E4049))

                        Row(
                            modifier = Modifier
                                .align(TopEnd)
                                .clip(shape = RoundedCornerShape(18.dp))
                                .width(45.dp)
                                .height(28.dp)
                                .background(togglePathColorState.value)
                                .clickable { toggleState.value = !toggleState.value },
                            verticalAlignment = CenterVertically
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
                            modifier = Modifier.align(BottomStart),
                            text = "기록을 공개하면, 다른 사람들도 나의 기록을볼 수 있습니다.",
                            fontFamily = pretendardRegular,
                            fontSize = 12.sp,
                            letterSpacing = (-0.25).sp,
                            color = Color(0xFF75777B)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(130.dp))
            }
        }

        Column(
            modifier = Modifier
                .align(BottomCenter)
                .fillMaxWidth()
                .height(121.dp)
        ) {
            val buttonClickedState = remember { mutableStateOf(false) }
            val nextButtonColorState = animateColorAsState(
                targetValue =
                if (titleTextState.value.isNotEmpty() && bodyTextState.value.isNotEmpty() && dateState.value != null && buttonClickedState.value) Color(0xBF3CEFA3)
                else if (titleTextState.value.isNotEmpty() && bodyTextState.value.isNotEmpty() && dateState.value != null) Color(0xFF3CEFA3)
                else Color(0xFF3E4049)
            )
            val nextButtonTextColorState = animateColorAsState(targetValue = if (titleTextState.value.isNotEmpty() && bodyTextState.value.isNotEmpty() && dateState.value != null) Color(0xFF1C1D27) else Color(0xFFA4A6AA))

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
                        .align(BottomCenter)
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
                                    onClickNext()
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
}

@Composable
private fun textSelectionColor() = TextSelectionColors(
    handleColor = Color(0XFF3CEFA3),
    backgroundColor = Color(0xFF282932)
)

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}