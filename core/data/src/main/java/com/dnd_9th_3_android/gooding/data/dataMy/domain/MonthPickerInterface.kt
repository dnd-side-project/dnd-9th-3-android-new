package com.dnd_9th_3_android.gooding.data.dataMy.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd_9th_3_android.gooding.model.month.MonthData

interface MonthPickerInterface {
    var monthDataList  : ArrayList<MonthData>
    var selectedIndex : Int
    var currentPickIndex : Int
    var _isChange : MutableLiveData<Boolean>
    val isChange : LiveData<Boolean>
    fun makeKeyData(year:Int, month:Int) : String
    fun makeDataList(startYear:Int,startMonth:Int,selectedIndex : Int,)
    fun getListData():List<MonthData>
    fun fixPicData(pickData : MonthData)
    fun resetData()
    fun setCurrentPickData()
}