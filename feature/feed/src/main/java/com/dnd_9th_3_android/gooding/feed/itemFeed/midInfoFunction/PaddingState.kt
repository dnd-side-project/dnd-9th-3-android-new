package com.dnd_9th_3_android.gooding.feed.itemFeed.midInfoFunction

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.dnd_9th_3_android.gooding.core.data.R
@Composable
fun PaddingState(
    extendState :Int
) : Dp {
    return when (extendState) {
        2 -> { // 2줄
            dimensionResource(id = R.dimen.line_2_padding)
        }
        3 -> { // 3줄
            dimensionResource(id = R.dimen.line_3_padding)
        }
        else -> { // 1줄
            dimensionResource(id = R.dimen.line_1_padding)
        }
    }
}