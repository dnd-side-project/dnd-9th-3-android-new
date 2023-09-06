package com.dnd_9th_3_android.gooding.my.type

import androidx.compose.runtime.Composable
import com.dnd_9th_3_android.gooding.core.data.R

@Composable
fun RecordScoreType(
    recordScore : Int,
) : Int {
    return when (recordScore) {
        in 0..20 -> {
            R.drawable.record_score_1
        }
        in 21..40 -> {
            R.drawable.record_score_2
        }
        in 41..60 -> {
            R.drawable.record_score_3
        }
        in 61..80 -> {
            R.drawable.record_score_4
        }
        else -> {
            R.drawable.record_score_5
        }
    }
}