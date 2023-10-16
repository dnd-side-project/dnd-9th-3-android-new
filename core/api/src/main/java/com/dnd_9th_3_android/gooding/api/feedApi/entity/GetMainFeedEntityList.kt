package com.dnd_9th_3_android.gooding.api.feedApi.entity

import com.dnd_9th_3_android.gooding.model.feed.GetMainFeed

data class GetMainFeedEntityList (
    val totalPages : Int,
    val totalElements : Int,
    val size : Int,
    val content : List<MainFeedEntity>,
):java.io.Serializable