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
            51.dp
        }
        3 -> { // 3줄
            31.dp
        }
        else -> { // 1줄
            36.dp
        }
    }
}