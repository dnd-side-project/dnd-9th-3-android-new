package com.dnd_9th_3_android.gooding.my.viewModel

import android.util.Log
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dnd_9th_3_android.gooding.data.dataMy.MonthPickerImpl
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MyOptionViewModel @Inject constructor(

) : ViewModel(){
    private var _applicationState : ApplicationState? = null
    val applicationState get() = _applicationState

    private var _screenWidth : Dp = 360.dp
    val screenWidth get() = _screenWidth

    // today Date , Month Picker
    var monthPicker = MonthPickerImpl()
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


    fun initAppState(
        appState : ApplicationState,
        appWidth : Dp
    ){
        _applicationState = appState
        _screenWidth = appWidth
    }

    fun naviToSetting(){
        Log.d("navi","navi to setting ")
        if (applicationState?.navController == null) {
            Log.d("navi", "navi not to setting ")
        }
        applicationState?.navController?.navigate(ScreenRoot.MY_SETTING)
    }
}