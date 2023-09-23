package com.dnd_9th_3_android.gooding.trash.data.model.search

import com.google.gson.annotations.SerializedName

data class PopularKeywordListResponse(
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String?,
    @SerializedName("result") val result: List<PopularKeywordResponse>
)