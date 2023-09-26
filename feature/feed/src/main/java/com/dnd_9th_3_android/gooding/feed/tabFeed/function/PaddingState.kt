package com.dnd_9th_3_android.gooding.feed.itemFeed.midInfoFunction

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dnd_9th_3_android.gooding.core.data.R
// refactoring

@Composable
fun PaddingState(
    extendState :Int
) : Dp {
    return when (extendState) {
        2 -> { // 2줄
            54.dp
        }
        3 -> { // 3줄
            34.dp
        }
        else -> { // 1줄
            38.dp
        }
    }
}