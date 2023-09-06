package com.dnd_9th_3_android.gooding.data.model.search

import com.google.gson.annotations.SerializedName

data class RecentKeywordListResponse(
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String?,
    @SerializedName("result") val result: List<RecentFeedResponse>
)
