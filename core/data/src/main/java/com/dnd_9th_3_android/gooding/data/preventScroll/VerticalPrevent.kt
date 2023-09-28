package com.dnd_9th_3_android.gooding.data.preventScroll

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.Velocity

private val VerticalScrollConsumerPrev = object : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource
    ) = available.copy(x = 0f)
    override suspend fun onPreFling(available: Velocity
    ) = available.copy(x = 0f)
}
fun Modifier.disabledVerticalPointerInputScrollPost(disabled: Boolean = true) =
    if (disabled) this.nestedScroll(VerticalScrollConsumerPrev) else this

private val VerticalScrollConsumerPost = object : NestedScrollConnection {

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
    ): Offset = available.copy(x = 0f)
    override suspend fun onPostFling(consumed: Velocity, available: Velocity
    ): Velocity = available.copy(x = 0f)
}
fun Modifier.disabledVerticalPointerInputScrollPrev(disabled: Boolean = true) =
    if (disabled) this.nestedScroll(VerticalScrollConsumerPost) else this