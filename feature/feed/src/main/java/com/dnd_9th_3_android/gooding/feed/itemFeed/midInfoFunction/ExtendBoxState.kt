package com.dnd_9th_3_android.gooding.feed.itemFeed.midInfoFunction

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.dnd_9th_3_android.gooding.core.data.R

@Composable
fun ExtendBoxState(
    extendState :Int
) : Dp {
    return when (extendState) {
        2 -> { // 2줄
            dimensionResource(id = R.dimen.line_3_box_h)
        }
        3 -> { // 3줄
            dimensionResource(id = R.dimen.line_2_box_h)
        }
        else -> { // 1줄
            dimensionResource(id = R.dimen.line_1_box_h)
        }
    }
}