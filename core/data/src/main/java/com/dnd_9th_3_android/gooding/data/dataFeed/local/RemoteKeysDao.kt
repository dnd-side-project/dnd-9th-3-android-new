package com.dnd_9th_3_android.gooding.data.dataFeed.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dnd_9th_3_android.gooding.api.feedApi.entity.RemoteKeysEntity

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRemote(list : List<RemoteKeysEntity>)

    @Query("SELECT * FROM remoteKey WHERE repoId = :id")
    fun getRemoteKeys(id : Int) : RemoteKeysEntity

    @Query("DELETE FROM remoteKey")
    fun clearAll()
}