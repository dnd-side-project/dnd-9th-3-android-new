package com.dnd_9th_3_android.gooding.data.sampleData

import android.app.appsearch.SearchResult
import com.dnd_9th_3_android.gooding.model.search.PopularData
import com.dnd_9th_3_android.gooding.model.search.SearchLog

object SampleSearchData {

    // 인기 검색 데이터
    val popularSampleData = listOf<PopularData>(
        PopularData(
            0,
            1,
            "조개구이"
        ),
        PopularData(
            1,
            2,
            "성수역"
        ),
        PopularData(
            2,
            3,
            "비 오는 날"
        ),
        PopularData(
            3,
            4,
            "전시"
        ),
    )

    // 최근 검색
    val recentlySampleData = listOf<SearchLog>(
        SearchLog(
            0,
            "한강"
        ),
        SearchLog(
            1,
            "서울숲역"
        ),
        SearchLog(
            2,
            "조개구이"
        ),
        SearchLog(
            3,
            "야경"
        ),
        SearchLog(
            0,
            "바다"
        ),
    )

    // 검색 결과 데이터
    val resultSampleData = listOf<SearchLog>(
        SearchLog(
            0,
            "바다"
        ),
        SearchLog(
            1,
            "바다 낚시"
        ),
        SearchLog(
            2,
            "바다 여행"
        ),
        SearchLog(
            3,
            "바닷가 여행"
        ),
        SearchLog(
            0,
            "바다가 보이는 마을"
        ),
        SearchLog(
            0,
            "바다 옆 바다"
        ),
    )

}