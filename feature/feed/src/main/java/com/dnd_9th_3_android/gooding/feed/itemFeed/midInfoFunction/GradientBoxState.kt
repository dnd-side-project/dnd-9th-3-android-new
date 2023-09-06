package com.dnd_9th_3_android.gooding.feed.itemFeed.midInfoFunction

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.dnd_9th_3_android.gooding.core.data.R
@Composable
fun GradientBoxState(
    extendState :Int
) : Dp {
    return if (extendState>1){ //확장 상태
        dimensionResource(id = R.dimen.bottom_box_h_extend)
    }else{ //기본
        dimensionResource(id = R.dimen.bottom_box_h)
    }
}