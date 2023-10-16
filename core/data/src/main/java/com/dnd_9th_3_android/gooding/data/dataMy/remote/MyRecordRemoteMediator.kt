package com.dnd_9th_3_android.gooding.data.dataMy.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.dnd_9th_3_android.gooding.api.NetworkManager
import com.dnd_9th_3_android.gooding.api.feedApi.entity.MainFeedEntity
import com.dnd_9th_3_android.gooding.api.myApi.entity.MyRecordEntity
import com.dnd_9th_3_android.gooding.api.remoteKey.RemoteKeysEntity
import com.dnd_9th_3_android.gooding.data.dataMy.local.database.MyRecordDatabase
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
class MyRecordRemoteMediator constructor(
    private val db : MyRecordDatabase,
    private val networkManager: NetworkManager,
    private val recordDate : String,
): RemoteMediator<Int,MyRecordEntity>(){
    private val startingPageIndex = 1

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }



    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MyRecordEntity>
    ): MediatorResult {
        val page = when (val pageKeyData = getKeyPageData(loadType,state)){
            is MediatorResult.Success ->{
                return pageKeyData
            }
            else ->{
                pageKeyData as Int
            }
        }

        try{
            val userData = networkManager.getUserData()!!
            val response = networkManager.getRecordApiService()
                .getMyDateRecords(userData.id,recordDate)

            val endOfList = response.isEmpty()
            db.withTransaction {
                if (loadType == LoadType.REFRESH){
                    db.remoteKeyDao().clearAll()
                    db.getMyRecordDao().clearAll()
                }
                val prevKey = if (page == startingPageIndex) null else page - 1
                val nextKey = if ( endOfList ) null else page + 1
                val keys = response.map {
                    RemoteKeysEntity(it.id,prevKey,nextKey)
                }
                db.remoteKeyDao().insertRemote(keys)
                db.getMyRecordDao().insert(response)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfList)
        }catch (e : Exception){
            return MediatorResult.Error(e)
        }
    }

    // load key 갱신
    private suspend fun getKeyPageData(
        loadType : LoadType,
        state : PagingState<Int, MyRecordEntity>
    ) : Any{
        return when(loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRefreshRemoteKey(state)
                remoteKeys?.nextKey?.minus(1)?: startingPageIndex
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                val prevKey = remoteKeys?.prevKey ?:MediatorResult.Success(
                    endOfPaginationReached = false
                )
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                val nextKey = remoteKeys?.nextKey ?:MediatorResult.Success(
                    endOfPaginationReached = true
                )
                nextKey
            }
        }
    }

    private suspend fun getFirstRemoteKey(state : PagingState<Int, MyRecordEntity>) : RemoteKeysEntity?{
        return withContext(Dispatchers.IO){
            state.pages
                .firstOrNull { it.data.isNotEmpty()}
                ?.data?.firstOrNull()
                ?.let{ record -> db.remoteKeyDao().getRemoteKeys(record.id)}
        }
    }

    private suspend fun getLastRemoteKey(state : PagingState<Int, MyRecordEntity>) : RemoteKeysEntity?{
        return withContext(Dispatchers.IO){
            state.pages
                .firstOrNull() { it.data.isNotEmpty()}
                ?.data?.lastOrNull()
                ?.let { record -> db.remoteKeyDao().getRemoteKeys(record.id)}
        }
    }

    private suspend fun getRefreshRemoteKey(state : PagingState<Int, MyRecordEntity>) : RemoteKeysEntity?{
        return withContext(Dispatchers.IO){
            state.anchorPosition?.let { index ->
                state.closestItemToPosition(index)?.id?.let{recordId ->
                    db.remoteKeyDao().getRemoteKeys(recordId)
                }
            }
        }
    }
}