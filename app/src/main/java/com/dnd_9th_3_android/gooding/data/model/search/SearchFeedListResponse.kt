package com.dnd_9th_3_android.gooding.data.model.search

import com.google.gson.annotations.SerializedName

data class SearchFeedListResponse(
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String?,
    @SerializedName("result") val result: List<SearchFeedResponse>
)

data class SearchFeedResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("profileName") val profileName: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("likes") val likes: Int,
)

fun SearchFeedResponse.toEntity(): SearchFeedData {
    return SearchFeedData(
        id = id,
        profileName = profileName,
        createdAt = createdAt,
        likes = likes
    )
}