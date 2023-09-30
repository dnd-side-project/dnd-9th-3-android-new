package com.dnd_9th_3_android.gooding.data.feedData.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dnd_9th_3_android.gooding.data.feedData.entity.MainFeedEntity
import com.dnd_9th_3_android.gooding.model.feed.model.MainFeed

@Dao
interface MainFeedDao {

    @Insert
    suspend fun upsetAll(items: List<MainFeedEntity>)

    // key - value
    @Query("Select * From items")
    fun pagingSource() : PagingSource<Int,MainFeedEntity>


    // 컬럼의 데이터를 가져오는 Dao
    @Query("SELECT * FROM items ORDER BY timestamp DESC")
    fun getItemPager() : PagingSource<Int, MainFeed>
}