package com.dnd_9th_3_android.gooding.my.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dnd_9th_3_android.gooding.data.sampleData.SampleRecordData
import com.dnd_9th_3_android.gooding.api.RetrofitUtil
import com.dnd_9th_3_android.gooding.api.myApi.entity.MyRecordEntity
import com.dnd_9th_3_android.gooding.data.dataMy.repository.MyRecordRepository
import com.dnd_9th_3_android.gooding.model.feed.MyFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TimeLineViewModel @Inject constructor(
    private val repository: MyRecordRepository
): ViewModel() {
    private val _currentTimeLineList =
        MutableStateFlow<PagingData<MyRecordEntity>>(PagingData.empty())
    var currentTimeLineList : Flow<PagingData<MyRecordEntity>> =
        _currentTimeLineList.asStateFlow()

    fun setCurrentTimeLine(recordDate : String) = viewModelScope.launch {
        currentTimeLineList = repository.getMyRecordPager(recordDate).cachedIn(viewModelScope)
    }
}