package com.dnd_9th_3_android.gooding.model.feed

import java.io.Serializable

data class MyFeedList (
    val feedList : ArrayList<MyFeed>
) : Serializable