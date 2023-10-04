package com.dnd_9th_3_android.gooding.my.selectMonth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialogProperties
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.data.component.BoxText
import com.dnd_9th_3_android.gooding.my.viewModel.MyOptionViewModel
import com.dnd_9th_3_android.gooding.my.viewModel.TodayViewModel
import com.holix.android.bottomsheetdialog.compose.BottomSheetBehaviorProperties
import kotlinx.coroutines.launch

@Composable
fun SelectMonthBottomSheet(
    todayViewModel : MyOptionViewModel = hiltViewModel(),
    onClose : () -> Unit
) {
    val listState = rememberLazyListState()
    val coroutineScope = todayViewModel.applicationState?.coroutineScope.let{coroutineScope ->
        coroutineScope ?: rememberCoroutineScope()
    }
    var click by remember{
        mutableStateOf(false)
    }

    BottomSheetDialog(
        onDismissRequest = {
            onClose()
        },
        properties = BottomSheetDialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false,
            behaviorProperties = BottomSheetBehaviorProperties(
                isDraggable = false
            )
        )
    ){
        Column(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.blue_gray_6),
                    shape = RoundedCornerShape(
                        topStart = dimensionResource(id = R.dimen.padding_24),
                        topEnd = dimensionResource(id = R.dimen.padding_24),
                    ),
                )
                .padding(
                    start = dimensionResource(id = R.dimen.padding_18),
                    bottom = dimensionResource(id = R.dimen.padding_18),
                )
                .wrapContentHeight()
                .fillMaxWidth()

        ){
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_36)))

            Row{
                Text(
                    text = "월 선택하기",
                    color = Color.White,
                    fontFamily = pretendardBold,
                    fontSize = dimensionResource(id = R.dimen.text_16_sp).value.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .clickable {
                            onClose()
                        }
                        .size(dimensionResource(id = R.dimen.padding_24)),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painter = painterResource(id = R.drawable.new_close_button),
                        contentDescription = null,
                        modifier = Modifier.wrapContentSize()
                    )
                }
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_13)))
            }
            
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_18)))

            LazyColumn(
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.size_144))
                    .fillMaxWidth()
                , state = listState
            ){
                todayViewModel.monthPicker.let{ monthPicker->
                    items(monthPicker.getListData()){data->
                        monthPicker.selectedIndex.let {
                            if (it>0){
                                coroutineScope.launch { listState.animateScrollToItem(it-1) }
                            }
                        }
                        ItemMonthData(data, onclick = { pick ->
                            // 데이터 수정 요청
                            monthPicker.fixPicData(pick)
                            click = true
                        })
                    }
                    // 클릭 감지 후 데이터 리셋
                    if (click){
//                        itemsIndexed(monthPicker.getListData()){ _,_-> }
                        click = false
                    }
                }
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_18)))

            // 완료 -> is change true
            Box(
                modifier = Modifier
                    .clickable {
                        todayViewModel.monthPicker.apply {
                            isChange = true
                            setCurrentPickData()
                        }
                        onClose()
                    }
                    .padding(end = dimensionResource(id = R.dimen.padding_18))
            ) {
                BoxText(
                    borderColor = listOf(
                        colorResource(id = R.color.blue_gray_7),
                        colorResource(id = R.color.blue_gray_7)
                    ),
                    borderShape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_8)),
                    borderBackground = colorResource(id = R.color.secondary_1),
                    text = "선택 완료",
                    fontSize = dimensionResource(id = R.dimen.text_16_sp).value.sp,
                    fontColor = colorResource(id = R.color.blue_gray_7),
                    hoPadding = dimensionResource(id = R.dimen.padding_11_5),
                    verPadding = dimensionResource(id = R.dimen.zero)
                )
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_10)))
        }
    }
}
