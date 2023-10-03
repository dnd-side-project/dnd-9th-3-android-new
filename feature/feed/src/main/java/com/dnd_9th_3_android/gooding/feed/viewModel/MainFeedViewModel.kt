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
import javax.inject.Inject

@HiltViewModel
class MainFeedViewModel @Inject constructor(
    repository: MainFeedRepository
): ViewModel(){
    var feedDataList : Flow<PagingData<MainFeedEntity>> =
        repository.getMainFeedPager().cachedIn(viewModelScope)

    var currentFeed = MutableLiveData<MainFeed>()

    fun setCurrentFeed(feed : MainFeedEntity?){
        currentFeed.value = feed?.toMainFeed()
    }

    fun setRomantic(per : Int){
        currentFeed.value?.recordScore = per
    }
}