package com.dnd_9th_3_android.gooding.data.dataMy.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dnd_9th_3_android.gooding.api.myApi.entity.MyRecordEntity

@Dao
interface MyRecordDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insert(items : List<MyRecordEntity>)

    @Query("SELECT * FROM myRecords")
    fun getItemPager() : PagingSource<Int,MyRecordEntity>

    @Query("DELETE FROM myRecords")
    fun clearAll()
}