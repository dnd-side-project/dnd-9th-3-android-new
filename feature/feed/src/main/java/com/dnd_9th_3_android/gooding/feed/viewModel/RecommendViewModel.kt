package com.dnd_9th_3_android.gooding.feed.viewModel

import android.icu.util.Calendar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(): ViewModel(){
    var _todayCalendar = MutableLiveData<Calendar>() //오늘 데이터
    val todayCalendar : LiveData<Calendar> get() = _todayCalendar
    var currentMonth : Int = 0
    var currentHotCount = 3
    init {
        _todayCalendar.value = Calendar.getInstance()
        currentMonth = todayCalendar.value!!.get(Calendar.MONTH)+1
    }

}