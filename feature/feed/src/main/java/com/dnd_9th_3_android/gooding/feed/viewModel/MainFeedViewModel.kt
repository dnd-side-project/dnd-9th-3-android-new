package com.dnd_9th_3_android.gooding.feed.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dnd_9th_3_android.gooding.api.feedApi.entity.MainFeedEntity
import com.dnd_9th_3_android.gooding.data.dataFeed.repository.MainFeedRepository
import com.dnd_9th_3_android.gooding.data.dataFeed.toMainFeed
import com.dnd_9th_3_android.gooding.model.feed.model.MainFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFeedViewModel @Inject constructor(
    private val repository: MainFeedRepository
): ViewModel(){
    private val _feedDataList =
        MutableStateFlow<PagingData<MainFeedEntity>>(PagingData.empty())
    var feedDataList : Flow<PagingData<MainFeedEntity>> =
        _feedDataList.asStateFlow()

    var currentFeed = MutableLiveData<MainFeed>()

    fun getMainFeedPagingData() = viewModelScope.launch {
        feedDataList = repository.getMainFeedPager().cachedIn(viewModelScope)
    }
    fun setCurrentFeed(feed : MainFeedEntity?){
        currentFeed.value = feed?.toMainFeed()
    }

    fun setRomantic(per : Int){
        currentFeed.value?.recordScore = per
    }
}