package com.dnd_9th_3_android.gooding.data.dataFeed.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dnd_9th_3_android.gooding.api.feedApi.entity.MainFeedEntity

@Dao
interface MainFeedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<MainFeedEntity>)

    // 컬럼의 데이터를 가져오는 Dao
    @Query("SELECT * FROM mainFeeds")
    fun getItemPager() : PagingSource<Int, MainFeedEntity>

    @Query("DELETE FROM mainFeeds")
    fun clearAll()
}