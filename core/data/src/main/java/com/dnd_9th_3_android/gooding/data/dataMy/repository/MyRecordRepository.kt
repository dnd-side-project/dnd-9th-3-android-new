package com.dnd_9th_3_android.gooding.data.dataMy.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dnd_9th_3_android.gooding.api.NetworkManager
import com.dnd_9th_3_android.gooding.api.myApi.entity.MyRecordEntity
import com.dnd_9th_3_android.gooding.data.dataMy.local.database.MyRecordDatabase
import com.dnd_9th_3_android.gooding.data.dataMy.remote.MyRecordRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MyRecordRepository @Inject constructor(
    private val db : MyRecordDatabase,
    private val networkManager: NetworkManager
){

    @OptIn(ExperimentalPagingApi::class)
    fun getMyRecordPager(recordDate : String) : Flow<PagingData<MyRecordEntity>>{
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ) ,
            pagingSourceFactory = {
                db.getMyRecordDao().getItemPager()
            },
            remoteMediator = MyRecordRemoteMediator(db,networkManager,recordDate)
        ).flow
    }
}