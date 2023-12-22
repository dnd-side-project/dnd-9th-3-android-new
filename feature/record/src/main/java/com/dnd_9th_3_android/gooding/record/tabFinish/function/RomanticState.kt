package com.dnd_9th_3_android.gooding.record.tabFinish.function

import com.dnd_9th_3_android.gooding.core.data.R

fun romanticIconState(level: Int): Int {
    return when(level){
        1 -> R.drawable.record_score_1
        2 -> R.drawable.record_score_2
        3 -> R.drawable.record_score_3
        4 -> R.drawable.record_score_4
        else -> R.drawable.record_score_5
    }
}

fun romanticMessageState(level: Int): String {
    return when(level){
        1 -> "낭만이긴 했어요"
        2 -> "소소한 낭만이었어요"
        3 -> "나름 낭만있었어요"
        4 -> "낭만 그 자체였어요"
        else -> "잊지못할 낭만이었어요"
    }
}