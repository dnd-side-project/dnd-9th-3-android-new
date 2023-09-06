package com.dnd_9th_3_android.gooding.data.model.map

import com.google.gson.annotations.SerializedName

data class KakaoMapListResponse(
    @SerializedName("list") val list: List<KakaoMapResponse>
)