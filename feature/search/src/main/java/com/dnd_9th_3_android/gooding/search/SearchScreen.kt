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
import androidx.compose.ui.text.input.TextFieldValue
import com.dnd_9th_3_android.gooding.data.sampleData.SampleSearchData
import com.dnd_9th_3_android.gooding.model.search.PopularData
import com.dnd_9th_3_android.gooding.model.search.SearchLog
import com.dnd_9th_3_android.gooding.search.ui.*

@Composable
fun SearchScreen(){
    val currentStep =  remember { mutableStateOf("인기") }
    val searchData = remember { mutableStateOf(TextFieldValue(""))}
    // 검색 로그
    val searchLogListState = remember { mutableStateListOf<SearchLog>() }
    // 인기 검색
    val popularListState = remember { mutableStateListOf<PopularData>() }
    // 검색 결과
    val searchResultListState = remember { mutableStateListOf<SearchLog>() }
    Column(
        Modifier
            .background(colorResource(id = R.color.blue_gray_7))
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(39.dp))
        SearchTopBar(
            searchData,
            backScreen = {
                // 임시 ! 꼭 삭제
                searchLogListState.clear()
                searchLogListState.addAll(SampleSearchData.recentlySampleData)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (searchData.value.text.isEmpty()){
            currentStep.value = "인기"
        }else{
            currentStep.value = "검색"
        }
        Column(Modifier.padding(horizontal = 18.dp)) {
            when (currentStep.value) {
                "인기" -> {
                    // 사용자 검색 로그
                    if (searchLogListState.isNotEmpty()) {
                        RecentlySearchScreen(searchLogListState)
                    }
                    // 인기 검색 데이터
                    PopularSearchScreen(popularListState)
                }
                "검색" -> {
                    // 검색 로그
                    if (searchResultListState.isNotEmpty()){
                        SearchResultScreen(
                            searchData,
                            searchResultListState
                        )
                    }else{
                        NoSearchDataScreen()
                        searchResultListState.clear()
                        searchResultListState.addAll(SampleSearchData.resultSampleData)
                    }
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