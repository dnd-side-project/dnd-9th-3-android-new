package com.dnd_9th_3_android.gooding.my.mainTabLayout

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.poppins
import com.dnd_9th_3_android.gooding.my.viewModel.TodayViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.model.feed.MyFeed
import com.dnd_9th_3_android.gooding.my.itemFeed.ItemMainFeedScreen
import com.dnd_9th_3_android.gooding.my.mainScreen.DefaultTimeLineScreen
import com.dnd_9th_3_android.gooding.my.selectMonth.SelectMonthBottomSheet
import com.dnd_9th_3_android.gooding.my.viewModel.CurrentTimeLineViewModel

@Composable
fun TimeLineScreen(
    todayViewModel: TodayViewModel = hiltViewModel(),
    timeLineViewModel : CurrentTimeLineViewModel = hiltViewModel()
) {
    // curent month data
    val currentKey = todayViewModel.monthPicker.monthDataList[todayViewModel.monthPicker.currentPickIndex].keyDate
    // is delete view ?
    var  showDeleteView by remember {
        mutableStateOf(false)
    }
    // is monthPicker view?
    var showSelectView by remember {
        mutableStateOf(false)
    }

    val todayDate = currentKey.replace(".","")
    timeLineViewModel.setCurrentTimeLine(1, todayDate)
    // 오류 있음 수정 필요
//    if (showDeleteView){
//        DeleteFeedBottomSheet(0,
//            onDelete = {
//                // delete feed/ //
//            },
//            onClose = {
//                // close view
//                showDeleteView = false
//            },
//        )
//    }
    if (showSelectView){
        SelectMonthBottomSheet(todayViewModel =todayViewModel,onClose = {
            todayViewModel.monthPicker.apply {
                if (!this.isChange){
                    this.resetData()
                }else{
                    // 데이터 초기화
                    this.isChange = false
                    timeLineViewModel.loadData = currentKey
                }
            }
            showSelectView = false

        })
    }
    // month picker view
    Column(
        // padding 적용 ! 모든 타임라인 18Dp
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = dimensionResource(id = R.dimen.padding_18),
                start = dimensionResource(id = R.dimen.padding_18),
            ),
        verticalArrangement = Arrangement.Center
    ) {
        // select month view
        Row(
            Modifier
                .clickable {
                    showSelectView = true
                }
                .wrapContentSize()
        ){
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.border_size)))
            Text(
                text = currentKey,
                fontFamily = poppins,
                fontSize = dimensionResource(id = R.dimen.main_text_sp).value.sp,
                color = Color.White
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(dimensionResource(id = R.dimen.padding_24))
            ){
                Image(
                    painter = painterResource(id = R.drawable.arrow_bottom),
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_6)))
        if (timeLineViewModel.currentTimeLine.isNotEmpty()){
            // get data
            FeedList(timeLineViewModel.currentTimeLine, onDeleteData = {
                showDeleteView = it
            })
        }else {
            // no user record
            DefaultTimeLineScreen()
        }
    }
}

@Composable
fun FeedList(feeds: SnapshotStateList<MyFeed>,onDeleteData : (Boolean)->Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(feeds) { feed ->
            ItemMainFeedScreen(feed, onDeleteView = {
                onDeleteData(it)
            })
        }
    }
}