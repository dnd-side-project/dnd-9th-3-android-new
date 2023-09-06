package com.dnd_9th_3_android.gooding.data.model.map

import com.google.gson.annotations.SerializedName

data class KakaoMapSameName(
    @SerializedName("keyword") val keyword: String,
    @SerializedName("region") val region: MutableList<String>,
    @SerializedName("selected_region") val selectedRegion: String,
)