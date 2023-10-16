package com.dnd_9th_3_android.gooding.api.myApi.mapping

import com.dnd_9th_3_android.gooding.api.feedApi.entity.MainFeedEntity
import com.dnd_9th_3_android.gooding.api.myApi.entity.MyRecordEntity
import com.dnd_9th_3_android.gooding.model.feed.FileData
import com.dnd_9th_3_android.gooding.model.feed.MyFeed


fun MyRecordEntity.toMyRecord() : MyFeed {
    return MyFeed(
        id,
        title,
        description,
        recordDate,
        placeTitle,
        placeLatitude,
        placeLongitude,
        recordScore,
        recordOpen,
        thumbnailUrl,
        files,
    )
}