package com.dnd_9th_3_android.gooding.data.feedData

import com.dnd_9th_3_android.gooding.api.feedApi.dto.MainFeedDto
import com.dnd_9th_3_android.gooding.api.feedApi.entity.MainFeedEntity
import com.dnd_9th_3_android.gooding.model.feed.model.MainFeed

fun MainFeedDto.toMainFeedEntity() : MainFeedEntity {
    return MainFeedEntity(
        recordId = recordId,
        title = title,
        description = description,
        placeTitle = placeTitle,
        placeLatitude = placeLatitude,
        placeLongitude = placeLongitude,
        thumbnailUrl = thumbnailUrl,
        interestType = interestType,
        user  = user,
        files = files,
        readCount = 0,
        isNumbering =  false,
        recordScore = 0
    )
}

fun MainFeedDto.toMainFeed() : MainFeed {
    return MainFeed(
        recordId = recordId,
        title = title,
        description = description,
        placeTitle = placeTitle,
        placeLatitude = placeLatitude,
        placeLongitude = placeLongitude,
        thumbnailUrl = thumbnailUrl,
        interestType = interestType,
        user  = user,
        files = files,
        readCount = 0,
        isNumbering =  false,
        recordScore = 0
    )
}
fun MainFeedEntity.toMainFeed() : MainFeed {
    return MainFeed(
        recordId,
        title,
        description,
        placeTitle,
        placeLatitude,
        placeLongitude,
        thumbnailUrl,
        interestType,
        user,
        files
    )
}
