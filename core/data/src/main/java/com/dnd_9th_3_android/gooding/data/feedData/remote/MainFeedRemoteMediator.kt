package com.dnd_9th_3_android.gooding.data.feedData.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.dnd_9th_3_android.gooding.data.feedData.entity.MainFeedEntity

@OptIn(ExperimentalPagingApi::class)
class MainFeedRemoteMediator (

):RemoteMediator<Int,MainFeedEntity>(){

    // 서버에서 어떤 값들을 가지고 와서 어떻게 db에 넣어서 보여줄 지 결정
    // load tye 지정
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MainFeedEntity>
    ): MediatorResult {
        TODO("Not yet implemented")
    }
}