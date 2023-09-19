package com.dnd_9th_3_android.gooding.search.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.contentLayout.pretendardBold
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.model.search.PopularData
import com.dnd_9th_3_android.gooding.model.search.SearchLog
import com.dnd_9th_3_android.gooding.search.item.PopularItem
import com.dnd_9th_3_android.gooding.search.item.RecentlyItem

@Composable
fun PopularSearchScreen(
    popularListState : SnapshotStateList<PopularData>
) {
    Column {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(color = colorResource(id = R.color.secondary_1))
                ) {
                    append("인기 굳이데이")
                }
                append(" 검색어")
            },
            fontFamily = pretendardBold,
            fontSize = 16.sp,
            color = Color.White
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ){
            itemsIndexed(popularListState){index,item ->
                PopularItem(
                    popularData = item,
                    clickData = {

                    }
                )
            }
        }
    }
}