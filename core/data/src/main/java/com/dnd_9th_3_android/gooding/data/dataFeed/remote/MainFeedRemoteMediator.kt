package com.dnd_9th_3_android.gooding.data.dataFeed.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.dnd_9th_3_android.gooding.api.NetworkManager
import com.dnd_9th_3_android.gooding.data.dataFeed.local.database.MainFeedDatabase
import com.dnd_9th_3_android.gooding.api.feedApi.entity.MainFeedEntity
import com.dnd_9th_3_android.gooding.api.remoteKey.RemoteKeysEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
class MainFeedRemoteMediator constructor(
    private val db : MainFeedDatabase,
    private val networkManager: NetworkManager,
):RemoteMediator<Int, MainFeedEntity>(){
    private val startingPageIndex = 1

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    // load tye 지정
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MainFeedEntity>
    ): MediatorResult {
        val page = when (val pageKeyData = getKeyPageData(loadType,state)) {
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                pageKeyData as Int
            }
        }

        try {
            val userData = networkManager.getUserData()!!
            val response = networkManager.getFeedApiService()
                .getUserFeedFromId(
                    userData.id, userData.onboardingStrings,
                    page, state.config.pageSize
                ).content
            val endOfList = response.isEmpty()
            db.withTransaction {
                if (loadType == LoadType.REFRESH){
                    db.remoteKeyDao().clearAll()
                    db.getMainFeedDao().clearAll()
                }
                val prevKey = if( page == startingPageIndex ) null else page - 1
                val nextKey = if ( endOfList ) null else page + 1
                val keys = response.map{
                    RemoteKeysEntity(it.recordId,prevKey,nextKey)
                }
                db.remoteKeyDao().insertRemote(keys)
                db.getMainFeedDao().insert(response)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfList)
        } catch (e : Exception) {
            return MediatorResult.Error(e)
        }
    }


    // load key 갱신
    private suspend fun getKeyPageData(
        loadType : LoadType,
        state : PagingState<Int, MainFeedEntity>
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

    private suspend fun getFirstRemoteKey(state : PagingState<Int, MainFeedEntity>) : RemoteKeysEntity?{
        return withContext(Dispatchers.IO){
            state.pages
                .firstOrNull { it.data.isNotEmpty()}
                ?.data?.firstOrNull()
                ?.let{ feed -> db.remoteKeyDao().getRemoteKeys(feed.recordId)}
        }
    }

    private suspend fun getLastRemoteKey(state : PagingState<Int, MainFeedEntity>) : RemoteKeysEntity?{
        return withContext(Dispatchers.IO){
            state.pages
                .firstOrNull() { it.data.isNotEmpty()}
                ?.data?.lastOrNull()
                ?.let { feed -> db.remoteKeyDao().getRemoteKeys(feed.recordId)}
        }
    }

    private suspend fun getRefreshRemoteKey(state : PagingState<Int, MainFeedEntity>) : RemoteKeysEntity?{
        return withContext(Dispatchers.IO){
            state.anchorPosition?.let { index ->
                state.closestItemToPosition(index)?.recordId?.let{recordId ->
                    db.remoteKeyDao().getRemoteKeys(recordId)
                }
            }
        }
    }
}