package com.dnd_9th_3_android.gooding.data.preventScroll

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.Velocity

// https://developer.android.com/reference/kotlin/androidx/compose/ui/input/nestedscroll/NestedScrollConnection
//포스트 스크롤 이벤트 패스. 이 패스는 디스패치(스크롤) 자손이 소비를 하고 조상에게 소비할 남은 것을 알릴 때 발생합니다.
private val HorizontalScrollConsumerPost = object : NestedScrollConnection {
    override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource)= available.copy(y = 0f)
    override suspend fun onPostFling(consumed: Velocity, available: Velocity)= available.copy(y = 0f)
//    override fun onPreScroll(available: Offset, source: NestedScrollSource) = available.copy(y = 0f)
//    override suspend fun onPreFling(available: Velocity) = available.copy(y = 0f)
}
fun Modifier.disabledHorizontalPointerInputScrollPost(disabled: Boolean = true) =
    if (disabled) this.nestedScroll(HorizontalScrollConsumerPost) else this

// 사전 스크롤 이벤트 체인. 부모가 드래그 이벤트의 일부를 미리 사용할 수 있도록 자식이 호출합니다.
private val HorizontalScrollConsumerPrev = object : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource) = available.copy(y = 0f)
    override suspend fun onPreFling(available: Velocity) = available.copy(y = 0f)
}
fun Modifier.disabledHorizontalPointerInputScrollPrev(disabled: Boolean = true) =
    if (disabled) this.nestedScroll(HorizontalScrollConsumerPrev) else this