package com.dnd_9th_3_android.gooding.data.remote.map

import com.dnd_9th_3_android.gooding.data.api.KakaoMapService
import com.dnd_9th_3_android.gooding.data.model.map.KakaoMapResponse
import javax.inject.Inject

class KakaoMapAddressRemoteDataSourceImpl @Inject constructor(
    private val kakaoMapService: KakaoMapService
) : KakaoMapAddressRemoteDataSource {
    override suspend fun getKakaoMapAddress(keyword: String): KakaoMapResponse {
        return kakaoMapService.getKakaoMapAddress(keyword)
    }
}