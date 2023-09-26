package com.dnd_9th_3_android.gooding.feed.itemFeed.midInfoFunction

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dnd_9th_3_android.gooding.core.data.R
// refactoring
// 문자열 확장 함수
@Composable
fun ExtendBoxState(
    extendState :Int
) : Dp {
    return when (extendState) {
        2 -> { // 2줄
            123.dp
        }
        3 -> { // 3줄
            105.dp
        }
        else -> { // 1줄
            85.dp
        }
    }
}