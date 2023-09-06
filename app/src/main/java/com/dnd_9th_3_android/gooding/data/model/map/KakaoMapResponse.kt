package com.dnd_9th_3_android.gooding.data.model.map

import com.google.gson.annotations.SerializedName

data class KakaoMapResponse(
    @SerializedName("documents") val documents: List<KakaoMapDocuments>,
    @SerializedName("meta") val meta: KakaoMapMeta
)

fun KakaoMapResponse.toEntity(): KakaoMapData {
    return KakaoMapData(
        documents = documents,
        meta = meta
    )
}