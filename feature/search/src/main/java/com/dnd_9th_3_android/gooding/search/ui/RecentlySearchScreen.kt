package com.dnd_9th_3_android.gooding.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.model.search.SearchLog
import com.dnd_9th_3_android.gooding.search.item.RecentlyItem

@Composable
fun RecentlySearchScreen(
    searchLogListState : SnapshotStateList<SearchLog>
) {
    Column {
        Box(Modifier.fillMaxWidth()){
            Text(
                text = "최근 검색어",
                modifier = Modifier.align(Alignment.CenterStart),
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = pretendardBold
            )
            Box(Modifier.clickable {
                searchLogListState.clear()
            }){
                Text(
                    text = "모두 지우기",
                    modifier = Modifier.align(Alignment.CenterEnd),
                    color = colorResource(id = R.color.blue_gray_3),
                    fontSize = 14.sp,
                    fontFamily = pretendardBold
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ){
            itemsIndexed(searchLogListState){index,item ->
                RecentlyItem(
                    item,
                    deleteData = {

                    },
                    clickData = {

                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(31.dp))
    }
}