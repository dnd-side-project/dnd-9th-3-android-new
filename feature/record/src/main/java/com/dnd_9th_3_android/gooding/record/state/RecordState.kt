package com.dnd_9th_3_android.gooding.record.state

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable

@Stable
class RecordState(
    val subject: MutableState<String>,
    val comment: MutableState<String>,
    val recordDate: MutableState<String>,
    val recordPlace: MutableState<String>,
    val recordCategory: MutableState<Int>,
    val toggleState: MutableState<Boolean>
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

    fun getSubjectSize() = subject.value.length

    fun checkSubjectEmpty() = subject.value.isEmpty()

    fun getCommentSize() = comment.value.length

    fun checkCommentEmpty() = comment.value.isEmpty()

    fun checkDateEmpty() = recordDate.value.ifEmpty { null }

    fun checkCurrentLocation() = recordPlace.value.isEmpty()

    fun setInitLocation() = if(checkCurrentLocation()) "카테고리를 선택해주세요.(선택)" else recordPlace.value

    fun checkCategoryText() = CATEGORY_TEXT_MAP[recordCategory.value].isNullOrEmpty()

    fun getCategoryText() = CATEGORY_TEXT_MAP[recordCategory.value] ?: "카테고리를 선택해주세요. (선택)"

    fun setToggleState() = toggleState.value != toggleState.value


    companion object {
        val CATEGORY_TEXT_MAP = HashMap<Int, String>().apply {
            set(1, "쇼핑")
            set(2, "여행")
            set(3, "미식")
            set(4, "독서")
            set(5, "요리")
            set(6, "문화")
            set(7, "스포츠")
            set(8, "취미")
            set(9, "학업")
            set(10, "이달의 굳이데이")
        }
    }
}