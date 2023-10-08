package com.dnd_9th_3_android.gooding.my.mainTabLayout

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.poppinsBold
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.model.feed.MyFeed
import com.dnd_9th_3_android.gooding.my.itemFeed.ItemMainFeedScreen
import com.dnd_9th_3_android.gooding.my.mainScreen.DefaultTimeLineScreen
import com.dnd_9th_3_android.gooding.my.viewModel.TimeLineViewModel
import com.dnd_9th_3_android.gooding.my.viewModel.MyOptionViewModel

@Composable
fun TimeLineScreen(
    optionViewModel: MyOptionViewModel = hiltViewModel(),
    timeLineViewModel : TimeLineViewModel = hiltViewModel()
) {
    // curent month data
    val currentKey = optionViewModel.monthPicker
        .monthDataList[optionViewModel.monthPicker.currentPickIndex].keyDate

    // is monthPicker view?
    val showSelectView = optionViewModel.myAccountState?.showMonthPickerView.let{ monthView ->
        monthView?: remember { mutableStateOf(false) }
    }

    // 수정 ! -> 유저 데이터 불러오기 적용 (페이징 )
    val todayDate = currentKey.replace(".","")
    timeLineViewModel.setCurrentTimeLine(1, todayDate)

    // month picker view
    Column(
        // padding 적용 ! 모든 타임라인 18Dp
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // select month view
        Row(
            Modifier
                .clickable { showSelectView.value = true }
                .wrapContentSize()
        ){
            Spacer(modifier = Modifier.width(19.dp))
            Text(
                text = currentKey,
                fontFamily = poppinsBold,
                fontSize = 18.sp,
                letterSpacing = (-0.25).sp,
                color = Color.White
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(24.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.arrow_bottom),
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        if (timeLineViewModel.currentTimeLine.isNotEmpty()){
            // get data
            FeedList(timeLineViewModel.currentTimeLine)
        }else {
            // no user record
            DefaultTimeLineScreen(
                goRecord ={
                    optionViewModel.naviToRecord()
                }
            )
        }
    }
}

@Composable
fun FeedList(feeds: SnapshotStateList<MyFeed>) {
    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(feeds) { feed ->
            ItemMainFeedScreen(feed)
        }
    }
}