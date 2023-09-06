package com.dnd_9th_3_android.gooding.data.model.search

import com.google.gson.annotations.SerializedName

data class PopularKeywordResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("content") val content: String,
    @SerializedName("searchedCount") val searchedCount: Int,
    @SerializedName("rank") val rank: Int,
)

fun PopularKeywordResponse.toEntity(): PopularKeywordData {
    return PopularKeywordData(
        id = id,
        content = content,
        searchedCount = searchedCount,
        rank = rank
    )
}