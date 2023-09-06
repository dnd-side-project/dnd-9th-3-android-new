package com.dnd_9th_3_android.gooding.login.viewModel

import androidx.compose.runtime.mutableStateListOf
import com.dnd_9th_3_android.gooding.login.type.CategoryListType
import com.dnd_9th_3_android.gooding.model.user.Category

object OnBoardingObject {
    var userName  : String = ""
    var categoryList = CategoryListType().categoryImageList
    var checkCategoryCount = 0
}