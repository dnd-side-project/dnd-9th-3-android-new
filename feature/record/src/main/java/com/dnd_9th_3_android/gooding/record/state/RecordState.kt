package com.dnd_9th_3_android.gooding.record.state

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import com.dnd_9th_3_android.gooding.data.type.CategoryListType
import com.dnd_9th_3_android.gooding.model.user.Category
import com.dnd_9th_3_android.gooding.core.data.R

@Stable
class RecordState(
    val subject: MutableState<String>,
    val comment: MutableState<String>,
    val recordDate: MutableState<String>,
    val recordPlace: MutableState<String>,
    val recordCategory: MutableState<Int>,
    val toggleState: MutableState<Boolean>
) {
    private var categoryList = listOf<Category>()

    init {
        categoryList = CategoryListType().categoryImageList.toMutableList().apply {
            add(
                Category(10, "이달의 굳이데이", false, R.drawable.record_score_1)
            )
        }.toList()
    }

    fun typeSubject(type: String) {
        subject.value = type
    }

    fun typeComment(type: String) {
        comment.value = type
    }

    fun setRecordDate(date: String) {
        recordDate.value = date
    }

    fun setRecordPlace(place: String) {
        recordPlace.value = place
    }

    fun setRecordCategory(index: Int) {
        recordCategory.value = index
    }

    fun getSubjectSize() = subject.value.length

    fun checkSubjectEmpty() = subject.value.isEmpty()

    fun getCommentSize() = comment.value.length

    fun checkCommentEmpty() = comment.value.isEmpty()

    fun checkDateEmpty() = recordDate.value.ifEmpty { null }

    fun checkCurrentLocation() = recordPlace.value.isEmpty()

    fun setInitLocation() = if (checkCurrentLocation()) "장소를 설정해주세요. (선택)" else recordPlace.value

    fun checkCategoryText() = recordCategory.value == -1

    fun getCategoryText() =
        if (recordCategory.value == -1) "카테고리를 선택해주세요. (선택)" else categoryList[recordCategory.value].name

    fun getCategoryList() = categoryList

    fun setToggleState() = toggleState.value != toggleState.value

    fun checkNextStep() = !checkSubjectEmpty() && !checkCommentEmpty() && checkDateEmpty() != null


}