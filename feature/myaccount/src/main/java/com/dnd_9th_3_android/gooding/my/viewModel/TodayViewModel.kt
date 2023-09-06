package com.dnd_9th_3_android.gooding.my.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dnd_9th_3_android.gooding.model.month.MonthData
import com.dnd_9th_3_android.gooding.my.selectMonth.data.MonthPickerImpl
import com.dnd_9th_3_android.gooding.my.selectMonth.data.MonthPickerInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class TodayViewModel @Inject constructor(monthPickerImpl: MonthPickerImpl): ViewModel() {
    var monthPicker = monthPickerImpl
    var _todayCalendar = MutableLiveData<Calendar>() //오늘 데이터
    val todayCalendar : LiveData<Calendar> get() = _todayCalendar
    var todayYear  = 0
    var todayMonth  = 0
    init {
        _todayCalendar.value = Calendar.getInstance()
        todayYear = todayCalendar.value!!.get(Calendar.YEAR)
        todayMonth = todayCalendar.value!!.get(Calendar.MONTH)+1
        monthPicker.makeDataList(todayYear,todayMonth,monthPicker.selectedIndex)
    }
}