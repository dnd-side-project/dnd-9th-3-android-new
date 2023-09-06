package com.dnd_9th_3_android.gooding.model.user

import java.io.Serializable

data class Category(
    val index : Int,
    val name : String,
    var selected : Boolean,
    val imageResource : Int,
) : Serializable