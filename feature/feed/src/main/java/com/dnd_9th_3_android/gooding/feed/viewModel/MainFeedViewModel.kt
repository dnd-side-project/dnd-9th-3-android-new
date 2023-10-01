package com.dnd_9th_3_android.gooding.feed.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dnd_9th_3_android.gooding.data.feedData.repository.MainFeedRepository
import com.dnd_9th_3_android.gooding.model.feed.model.MainFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainFeedViewModel @Inject constructor(
    private val repository: MainFeedRepository
): ViewModel(){
    val feedDataList : Flow<PagingData<MainFeed>> =
        repository.getMainFeedPager()
            .cachedIn(viewModelScope)
    var currentFeed = MutableLiveData<MainFeed>()


    fun setCurrentFeed(feed : MainFeed){
        currentFeed.value = feed
    }

    fun setRomantic(per : Int){
        currentFeed.value?.recordScore = per
    }
}