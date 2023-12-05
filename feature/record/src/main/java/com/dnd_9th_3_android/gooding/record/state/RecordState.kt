package com.dnd_9th_3_android.gooding.record.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable

@Stable
class RecordState(
    val subject: MutableState<String>,
    val comment: MutableState<String>,
    val recordDate: MutableState<String>,
    val recordPlace: MutableState<String>,
    val recordCategory: MutableState<Int>
) {

    fun typeSubject(type: String){
        subject.value = type
    }

    fun typeComment(type: String){
        comment.value = type
    }

    fun setRecordDate(date: String){
        recordDate.value = date
    }

    fun setRecordPlace(place: String){
        recordPlace.value = place
    }

    fun setRecordCategory(index: Int){
        recordCategory.value = index
    }
}