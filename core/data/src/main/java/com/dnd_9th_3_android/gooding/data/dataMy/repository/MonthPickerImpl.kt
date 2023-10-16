package com.dnd_9th_3_android.gooding.data.dataMy.repository

import com.dnd_9th_3_android.gooding.data.dataMy.domain.MonthPickerInterface
import com.dnd_9th_3_android.gooding.model.month.MonthData
import javax.inject.Inject

class MonthPickerImpl @Inject constructor(
) : MonthPickerInterface {
    override var monthDataList =  ArrayList<MonthData>()
    override var selectedIndex: Int = 0 //현재 뷰 데이터
    override var currentPickIndex: Int = 0 //이전 데이터
    override var isChange: Boolean = false
    override fun makeKeyData(year: Int, month: Int) : String {
        return if (month < 10){
            "$year.0$month"
        }else {
            "$year.$month"
        }
    }

    override fun makeDataList(
        startYear: Int,
        startMonth: Int,
        selectedIndex : Int,
    ) {
        var year = startYear
        var month = startMonth
        for (i in 0 until 100) {
            val isSelected = i == selectedIndex
            monthDataList.add(
                MonthData(makeKeyData(year, month), year, month,isSelected)
            )
            if (month == 1) {
                year -= 1
                month = 12
            } else {
                month -= 1
            }
        }
    }

    override fun getListData(): List<MonthData> {
        return monthDataList.toList()
    }

    override fun fixPicData(pickData: MonthData) {
        monthDataList[selectedIndex].isSelected = false
        selectedIndex = monthDataList.indexOf(pickData)
        monthDataList[selectedIndex].isSelected = true
    }

    // 선택 취소 시 되돌리기
    override fun resetData() {
        monthDataList[selectedIndex].isSelected = false
        monthDataList[currentPickIndex].isSelected = true
        selectedIndex = currentPickIndex
    }

    override fun setCurrentPickData() {
        currentPickIndex = selectedIndex
    }


}