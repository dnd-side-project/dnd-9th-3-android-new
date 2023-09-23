package com.dnd_9th_3_android.gooding.trash.data.model.map

import com.google.gson.annotations.SerializedName

data class KakaoMapMeta(
    @SerializedName("is_end") val isEnd: Boolean,
    @SerializedName("pageable_count") val pageableCount: Int,
    @SerializedName("same_name") val sameName: KakaoMapSameName,
    @SerializedName("total_count") val totalCount: Int,
)