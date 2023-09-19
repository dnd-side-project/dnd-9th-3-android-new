package com.dnd_9th_3_android.gooding.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dnd_9th_3_android.gooding.core.data.R
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.dnd_9th_3_android.gooding.model.search.PopularData
import com.dnd_9th_3_android.gooding.model.search.SearchLog
import com.dnd_9th_3_android.gooding.search.ui.NoSearchDataScreen
import com.dnd_9th_3_android.gooding.search.ui.PopularSearchScreen
import com.dnd_9th_3_android.gooding.search.ui.RecentlySearchScreen
import com.dnd_9th_3_android.gooding.search.ui.SearchTopBar

@Composable
fun SearchScreen(){
    val currentStep =  remember { mutableStateOf("인기") }
    val searchLogListState = remember { mutableStateListOf<SearchLog>() }
    val popularListState = remember { mutableStateListOf<PopularData>() }
    Column(
        Modifier
            .background(colorResource(id = R.color.blue_gray_7))
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(39.dp))
        SearchTopBar(backScreen = {

        })
        Spacer(modifier = Modifier.height(16.dp))

        Box(Modifier.padding(horizontal = 18.dp)) {
            when (currentStep.value) {
                "인기" -> {
                    // 인기 검색 데이터
                    PopularSearchScreen(popularListState)
                }

                "검색" -> {
                    // 검색 로그
                    if (searchLogListState.isNotEmpty()) {
                        RecentlySearchScreen(searchLogListState)
                    } else {
                        NoSearchDataScreen()
                    }
                }

                "데이터 없음" -> {

                }
            }
        }

    }
}


@Composable
@Preview
fun SearchPreview(){
    SearchScreen()
}