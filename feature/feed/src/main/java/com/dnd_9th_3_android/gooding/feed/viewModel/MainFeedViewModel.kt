package com.dnd_9th_3_android.gooding.feed.viewModel

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dnd_9th_3_android.gooding.api.RetrofitUtil
import com.dnd_9th_3_android.gooding.api.UserApiService
import com.dnd_9th_3_android.gooding.api.UserInfo
import com.dnd_9th_3_android.gooding.data.SampleFeedDataList
import com.dnd_9th_3_android.gooding.data.feedData.repository.MainFeedRepository
import com.dnd_9th_3_android.gooding.model.feed.Feed
import com.dnd_9th_3_android.gooding.model.feed.GetMainFeed
import com.dnd_9th_3_android.gooding.model.feed.GetMainFeedList
import com.dnd_9th_3_android.gooding.model.feed.model.MainFeed
import com.dnd_9th_3_android.gooding.model.user.AccessToken
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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