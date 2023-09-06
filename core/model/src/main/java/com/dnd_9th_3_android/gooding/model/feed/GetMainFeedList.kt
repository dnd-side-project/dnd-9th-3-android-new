package com.dnd_9th_3_android.gooding.model.feed

data class GetMainFeedList (
    val totalPages : Int,
    val totalElements : Int,
    val size : Int,
    val content : ArrayList<GetMainFeed>,
):java.io.Serializable