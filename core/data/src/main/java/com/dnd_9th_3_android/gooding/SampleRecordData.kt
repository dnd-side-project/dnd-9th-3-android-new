package com.dnd_9th_3_android.gooding

import com.dnd_9th_3_android.gooding.model.feed.FileData
import com.dnd_9th_3_android.gooding.model.feed.MyFeed

object SampleRecordData {
    var sampleRecordData : MyFeed? = null


    val newData = MyFeed(
        id = 0,
        title  = "제주도 당일치기",
        description = "제주도 당일치기 여행을 다녀왔다!!",
        recordDate = "2023-08-25T20:49:09.230Z",
        placeTitle = "제주특별자치도 제주시 오동동 산 182",
        0.toDouble(),
        0.toDouble(),
        100,
        "true",
        "https://dnd9th-3.s3.ap-northeast-2.amazonaws.com/images/6be88bdd-99b9-4c84-831c-e169f39af82f1317a38b-f6bb-48df-9ffd-3fc719c2e8f2.jpeg",
        arrayListOf(
            FileData(
             0,
                "https://dnd9th-3.s3.ap-northeast-2.amazonaws.com/images/6be88bdd-99b9-4c84-831c-e169f39af82f1317a38b-f6bb-48df-9ffd-3fc719c2e8f2.jpeg",
            ),
            FileData(
             1,
                "https://dnd9th-3.s3.ap-northeast-2.amazonaws.com/images/11db12e9-c072-43b9-92bc-c8c988692573c6fc209c-3bc6-457f-883b-70dce4a063e8.jpeg",
            )
        )
    )
}