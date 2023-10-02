package com.dnd_9th_3_android.gooding.data.feedData.repository

import android.app.Application
import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dnd_9th_3_android.gooding.api.NetworkManager
import com.dnd_9th_3_android.gooding.api.feedApi.FeedRetrofitService
import com.dnd_9th_3_android.gooding.api.feedApi.dto.MainFeedDto
import com.dnd_9th_3_android.gooding.api.feedApi.entity.MainFeedEntity
import com.dnd_9th_3_android.gooding.data.feedData.database.MainFeedDatabase
import com.dnd_9th_3_android.gooding.data.feedData.local.MainFeedDao
import com.dnd_9th_3_android.gooding.data.feedData.remote.MainFeedRemoteMediator
import com.dnd_9th_3_android.gooding.model.feed.model.MainFeed
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainFeedRepository @Inject constructor(
    private val db : MainFeedDatabase,
    private val networkManager : NetworkManager
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getMainFeedPager() : Flow<PagingData<MainFeedEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                db.getMainFeedDao().getItemPager()
            },
            remoteMediator = MainFeedRemoteMediator(db,networkManager)
        ).flow
    }
}