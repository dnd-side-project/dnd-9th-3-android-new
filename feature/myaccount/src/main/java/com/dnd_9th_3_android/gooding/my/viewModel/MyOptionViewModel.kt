package com.dnd_9th_3_android.gooding.my.viewModel

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dnd_9th_3_android.gooding.data.dataMy.repository.MonthPickerImpl
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.data.state.MyAccountState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MyOptionViewModel @Inject constructor(
    val monthPicker : MonthPickerImpl
) : ViewModel(){
    private var _applicationState : ApplicationState? = null
    val applicationState get() = _applicationState

    private var _screenWidth : Dp = 360.dp
    val screenWidth get() = _screenWidth

    private var _myAccountState : MyAccountState? = null
    val myAccountState get() = _myAccountState

    private var todayCalendar = MutableLiveData<Calendar>() //오늘 데이터
    init {
        todayCalendar.value = Calendar.getInstance()
        val todayYear = todayCalendar.value!!.get(Calendar.YEAR)
        val todayMonth = todayCalendar.value!!.get(Calendar.MONTH)+1
        monthPicker.makeDataList(todayYear,todayMonth,monthPicker.selectedIndex)
    }


    fun initAppState(appState : ApplicationState, appWidth : Dp){
        _applicationState = appState
        _screenWidth = appWidth
    }

    fun initMyAccountState(accountState: MyAccountState){
        _myAccountState = accountState
    }

    fun closeMonthPicker(){
        if (!monthPicker.isChange.value!!){
            monthPicker.resetData()
        } else{
            monthPicker._isChange.value = false
        }
        myAccountState?.showMonthPickerView?.value = false
    }

    fun changeMonthData(){
        monthPicker._isChange.value = true
        monthPicker.setCurrentPickData()
        closeMonthPicker()
    }
    fun naviToSetting(){
        applicationState?.navController?.navigate(ScreenRoot.MY_SETTING)
    }

    fun naviToRecord(){
        applicationState?.navController?.navigate(ScreenRoot.MAIN_RECORD)
    }

}