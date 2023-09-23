package com.dnd_9th_3_android.gooding.trash.data.remote.map

import com.dnd_9th_3_android.gooding.data.model.map.KakaoMapResponse

interface KakaoMapAddressRemoteDataSource {
    suspend fun getKakaoMapAddress(keyword: String): KakaoMapResponse
}