package com.dnd_9th_3_android.gooding.data.feedData.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dnd_9th_3_android.gooding.api.feedApi.FeedRetrofitService
import com.dnd_9th_3_android.gooding.data.feedData.local.MainFeedDao
import com.dnd_9th_3_android.gooding.model.feed.model.MainFeed
import kotlinx.coroutines.flow.Flow

class MainFeedRepository(
    private val dao : MainFeedDao,
    private val feedApiService : FeedRetrofitService
) {
    fun getMainFeedPager() : Flow<PagingData<MainFeed>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                dao.getItemPager()
            }
        ).flow
    }
}