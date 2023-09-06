package com.dnd_9th_3_android.gooding.data.model.search

import com.google.gson.annotations.SerializedName

data class RecentFeedResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("content") val content: String,
)

fun RecentFeedResponse.toEntity(): RecentKeywordData {
    return RecentKeywordData(
        id = id,
        content = content
    )
}